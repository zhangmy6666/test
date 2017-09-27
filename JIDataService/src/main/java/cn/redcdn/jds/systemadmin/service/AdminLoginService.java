package cn.redcdn.jds.systemadmin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.redcdn.jds.common.api.LoginApiService;
import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.SystemAdmin;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;
import cn.redcdn.jds.common.util.Constants;
import cn.redcdn.jds.common.util.MD5Util;
import cn.redcdn.jds.common.util.StringUtil;
import cn.redcdn.jds.systemadmin.dao.AdminLoginDao;
import cn.redcdn.jds.systemadmin.dto.AdminLoginDto;

@Service("adminLogin")
public class AdminLoginService extends BaseService<AdminLoginDto> {
	
	/**
	 * 系统管理员
	 */
	@Autowired
	private AdminLoginDao adminLoginDao;
	
	/**
	 * 缓存
	 */
	@Autowired
	private TokenApiService tokenApiService;
	
	@Autowired
	private LoginApiService loginApiService;
	
	
	
	/**
     * process 处理POST请求返回结果，包括返回码和返回码描述
     * 
     * @param params 业务参数
     * @param request request对象
	 * @param response response对象
     * @return 返回码及描述
     */
    @Override
    public ResponseDto<AdminLoginDto> process(JSONObject params, 
        HttpServletRequest request, HttpServletResponse response)
    {
    	// 必须check
    	CheckUtil.checkEmpty(params, "account", "password");
    	
    	// 账号
    	String account = params.getString("account");
    	// 密码
    	String password = MD5Util.getMd5(params.getString("password"));
    	MessageInfoDto messageInfo = loginApiService.getLoginErrorCount(Constants.ACCOUNT_TYPE_SYSADMIN, account);
    	if(messageInfo.getCode() < 0){
    		throw new ExternalServiceException(messageInfo);
    	}
    	SystemAdmin adminInfo = adminLoginDao.getSystemAdminInfo(account, password);
    	if (adminInfo == null)
    	{
    		messageInfo = loginApiService.loginPassWordError(Constants.ACCOUNT_TYPE_SYSADMIN, account);
    		throw new ExternalServiceException(messageInfo);
    	}
    	// 参数获取params
        ResponseDto<AdminLoginDto> rspDto = new ResponseDto<AdminLoginDto>();
        AdminLoginDto adminLoginDto = new AdminLoginDto();
        String token = StringUtil.getUUIDString();
        // 放入缓存
        tokenApiService.setSystemToken(token);
        tokenApiService.setTokenCreator(token, adminInfo.getAccount());
        adminLoginDto.setToken(token);
        loginApiService.loginSuccess(Constants.ACCOUNT_TYPE_SYSADMIN, account);
        rspDto.setData(adminLoginDto);  
        
        return rspDto;
    }
}
