package cn.redcdn.jds.common.api;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.redcdn.jds.common.util.PropertiesUtil;

/**
 * <dl>
 * <dt>UserApiService.java</dt>
 * <dd>Description: 医疗用户中心接口封装</dd>
 * <dd>Copyright: Copyright (C) 2017</dd>
 * <dd>Company: 北京红云融通技术有限公司</dd>
 * <dd>CreateDate: 2017-2-22</dd>
 * </dl>
 * 
 * @author Qbian
 */
@Service
public class UserCenterApiService extends BaseApiService {
	
	private final String USER_SAAS_URL = PropertiesUtil.getProperty("user_saas_url");
	
	public JSONObject checkUserToken(String token) {
		return getWithNotException(USER_SAAS_URL + "/BaikuUserCenterV2/passportService?service=verifyToken&accessToken=" + token);
	}

	public JSONObject getUserInfo(String token) {
		JSONObject param = new JSONObject();
		param.put("service", "getUserInfo");
		param.put("accessToken", token);
		return postForm(USER_SAAS_URL + "/BaikuUserCenterV2/auth", param); 
	}
}
