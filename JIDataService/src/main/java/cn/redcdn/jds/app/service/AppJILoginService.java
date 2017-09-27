package cn.redcdn.jds.app.service;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cn.redcdn.jds.app.dto.UserInfoDto;
import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.api.TypeApiService;
import cn.redcdn.jds.common.api.UserCenterApiService;
import cn.redcdn.jds.common.async.CallBack;
import cn.redcdn.jds.common.async.ThreadExecutor;
import cn.redcdn.jds.common.dao.UserInfoDao;
import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;
import cn.redcdn.jds.common.util.MessageUtil;
import cn.redcdn.jds.common.util.StringUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户登录接口
 * @author Qbian
 *
 */
@Service("appJILogin")
public class AppJILoginService extends BaseService<UserInfoDto> {
	
	@Autowired 
	UserCenterApiService userCenterApiService;
	
	@Autowired 
	UserInfoDao userInfoDao;
	
	@Autowired 
	TokenApiService tokenApiService; 
	
	@Autowired 
	TypeApiService typeApiService;
	
	/**
     * process 处理POST请求返回结果，包括返回码和返回码描述
     * 
     * @param params 业务参数
     * @param request request对象
	 * @param response response对象
     * @return 返回码及描述
     */
	@Override
	public ResponseDto<UserInfoDto> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
		// 校验请求参数
		CheckUtil.checkEmpty(params, "token");
		
		String token = params.getString("token");
		
		// 缓存里面有没有nube号
		String nube = tokenApiService.getNubeByToken(token);
		
		// 返回数据
		ResponseDto<UserInfoDto> rspDto = new ResponseDto<UserInfoDto>();
		
		if (StringUtils.isEmpty(nube)) {
			// 用户中心登录
			JSONObject obj = userCenterApiService.getUserInfo(token);
			UserInfo userInfo = getUserData(obj.getJSONObject("userInfo").getString("nubeNumber"));
			if (userInfo != null) {
				// 设定返回数据
				UserInfoDto userInfoDto = getUserInfoDtoExist(userInfo, token);
				rspDto.setData(userInfoDto);
				//将 id放入缓存
				tokenApiService.setToken(token, userInfo.getId());
				//将nube放入缓存
				tokenApiService.setNubeByToken(token, userInfo.getNube());
			} else {
				// 设定返回数据
				UserInfoDto userInfoDto = setUserInfoDto(obj, token);
				rspDto.setData(userInfoDto);
				// 异步DB追加数据
				ThreadExecutor.excute(
						new CallBack() {
							public void process() {
								// 用户表追加数据
								UserInfo userInfo = addUserInfo(userInfoDto);
								//将 id放入缓存
								tokenApiService.setToken(token, userInfo.getId());
								//将nube放入缓存
								tokenApiService.setNubeByToken(token, userInfo.getNube());
							}});
			}
		} else {
			// 从DB取得登录用户的数据
			UserInfo userInfo = getUserData(nube);
			if (userInfo == null) {
				MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("nube.search.error");
				String message = messageInfo.getMsg();
				message = MessageFormat.format(message, nube);
				messageInfo.setMsg(message);
				throw new ExternalServiceException(messageInfo);
			}
			// 设定返回数据
			UserInfoDto userInfoDto = getUserInfoDtoExist(userInfo, token);
			rspDto.setData(userInfoDto);
		}
		
		return rspDto;
	}
	
	/**
     * 检查账号是否存在
     * 
     * @param obj 用户中心返回的数据
     * @return DB查询的账号数据
     */
	public UserInfo getUserData(String nube) {
		List<UserInfo> userInfoList = userInfoDao.queryByField("nube", nube);
		if (!CollectionUtils.isEmpty(userInfoList)) {
			return userInfoList.get(0);
		} else {
			return null;
		}
	}
	
	/**
     * 设定返回数据
     * 
     * @param obj 用户中心返回数据
     * @return UserInfoDto
     */
	public UserInfoDto setUserInfoDto(JSONObject obj, String token) {
		UserInfoDto userInfoDto = new UserInfoDto();
		JSONObject userInfoJson = obj.getJSONObject("userInfo");
		// 登录鉴权token
		userInfoDto.setToken(token);
		// 视讯号
		userInfoDto.setNube(userInfoJson.getString("nubeNumber"));
		// 参会名称
		userInfoDto.setNickName(StringUtil.EMPTY);
		// 用户手机号
		userInfoDto.setMobile(userInfoJson.getString("mobile"));
		// 用户邮箱
		userInfoDto.setMail(userInfoJson.getString("mail"));
		// 用户类型，见业务码说明 2.2用户类型
		userInfoDto.setUserType("2");
		// 头像
		userInfoDto.setHeadUrl(StringUtil.EMPTY);
		// 服务有效时间
		userInfoDto.setExpiryDate(null);
		
		return userInfoDto;
	}
	
	/**
     * 设定返回数据
     * 
     * @param userInfo 用户数据
     * @param token token
     * @return UserInfoDto
     */
	public UserInfoDto getUserInfoDtoExist(UserInfo userInfo, String token) {

		UserInfoDto userInfoDto = new UserInfoDto();
		BeanUtils.copyProperties(userInfo, userInfoDto);
		// 登录鉴权token
		userInfoDto.setToken(token);
		// 用户类型，见业务码说明 2.2用户类型
		userInfoDto.setUserType(typeApiService.getUserType(userInfo.getProDate(), userInfo.getExpiryDate()));
		
		return userInfoDto;
	}
	
	/**
     * 用户表追加数据
     * 
     * @param userInfoDto response返回用户数据
     * @return UserInfo 用户信息
     */
	public UserInfo addUserInfo(UserInfoDto userInfoDto) {
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(userInfoDto, userInfo);
		// 试用载止日期
		userInfo.setProDate(typeApiService.getProDate());
		userInfoDao.insert(userInfo);
		return userInfo;
	}
}
