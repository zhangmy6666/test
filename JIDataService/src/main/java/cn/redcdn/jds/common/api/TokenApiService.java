package cn.redcdn.jds.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.redcdn.jds.common.dao.CacheDao;
import cn.redcdn.jds.common.dao.UserInfoDao;
import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.util.MessageUtil;
import cn.redcdn.jds.common.util.PropertiesUtil;
import cn.redcdn.jds.common.util.StringUtil;

import com.alibaba.fastjson.JSONObject;

@Service
public class TokenApiService {
    
    private final Logger logger = LoggerFactory.getLogger(TokenApiService.class);
    
    private final String expire_time = PropertiesUtil.getProperty("token_expire_time");
    
    @Autowired UserCenterApiService userCenterApiService;
    
    @Autowired CacheDao cacheDao;    

	@Autowired 
	UserInfoDao userInfoDao;

    /**  
     * 设置用户token 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public void setToken(String token, String id) {  
        // 四十天有效
        cacheDao.set("app_id_" + token, id, 40 * 24 *60 *60); 
        // 把以前的旧token清空
        String oldToken = cacheDao.get("token_uid_" + id);
        if (StringUtil.isNotBlank(oldToken))
        {
        	cacheDao.delete("exist_" + oldToken);
        }
        cacheDao.set("token_uid_" + id, token, 40 * 24 *60 *60); 
        int time = Integer.parseInt(expire_time) * 60 * 60;
        cacheDao.set("exist_" + token, "valid", time);
    }    
    
    /**
     * 设置公众号登录的token
     * @param token
     * @param VchannelId
     */
    public void setSystemToken(String token){
        // 四十天有效
        cacheDao.set("system_exist_" + token, "valid", 40 * 24 *60 *60);
    }
    
    /**  
     * 设置token有效 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public void setTokenValid(String token) {  
        int time = Integer.parseInt(expire_time) * 60 * 60;
        cacheDao.set("exist_" + token, "valid", time);
    }
    
    /**  
     * 设置token有效 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public void setTokenCreator(String token, String account) {  
        cacheDao.set("creator_" + token, account, 40 * 24 * 60 * 60);
    }    
    
    /**  
     * 设置token有效 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public String getCreatorByToken(String token) {  
        return cacheDao.get("creator_" + token);
    }

    /**  
     * 设置用户token 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public void deleteToken(String token) {  
        cacheDao.delete("app_id_" + token);
        cacheDao.delete("creator_" + token);
        cacheDao.set("exist_" + token, "invalid", 40 * 24 *60 *60);
    }

    /**  
     * 设置用户token 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public void deleteSystemToken(String token) {  
        cacheDao.delete("system_exist_" + token);
    }
    
    /**
     * 根据token获取用户id
     * @param token 
     * @return
     */
    public String getIdByToken(String token) {
        return cacheDao.get("app_id_" + token);
    }
    
    /**
     * app 用户登录时使用
     * @param token
     * @return
     */
    public void checkUserLoginToken(String token) {
        String existToken = cacheDao.get("exist_" + token);

        if("valid".equals(existToken))
        {
            return;
        }
        else if("invalid".equals(existToken))
        {
            MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("token.invalid");
            throw new ExternalServiceException(messageInfo);
        }
        else
        {
            // 2、查询用户中心
            JSONObject checkTokenResult = userCenterApiService.checkUserToken(token);
            logger.info("校验用户token结果{}", new Object[]{checkTokenResult});
            if("0".equals(checkTokenResult.getString("status"))) {
                setTokenValid(token);
                return;
            }
            MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("token.invalid");
            throw new ExternalServiceException(messageInfo);
        }
    }

    /**
     * 公众号token验证
     * @param token
     */
    public void checkSystemLoginToken(String token) {
        String existToken = cacheDao.get("system_exist_" + token);
        if(!"valid".equals(existToken))
        {
            MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("token.invalid");
            throw new ExternalServiceException(messageInfo);
        }
    }
    
    /**
     * 设置token对应的视频号
     * @param token
     * @param nube
     */
    public void setNubeByToken(String token, String nube){
        // 四十天有效
        cacheDao.set("app_nube_" + token, nube, 40 * 24 *60 *60);
    }
    
    /**  
     * 取得token对应的视讯号 
     *   
     * @param key  
     * @param value  
     * @return  
     */  
    public String getNubeByToken(String token) {  
        return cacheDao.get("app_nube_" + token);
    }
}
