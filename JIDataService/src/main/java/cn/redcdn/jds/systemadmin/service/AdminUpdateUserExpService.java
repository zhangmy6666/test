package cn.redcdn.jds.systemadmin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.dao.UserInfoDao;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;
import cn.redcdn.jds.common.util.DateUtil;

@Service("adminUpdateUserExp")
public class AdminUpdateUserExpService extends BaseService<String> {

	@Autowired
	private TokenApiService tokenApiService;
	
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {
		// 必须check
		CheckUtil.checkEmpty(params, "token", "expiryDate", "id");

		// token校验
		String token = params.getString("token");
		tokenApiService.checkSystemLoginToken(token);

		String id = params.getString("id");
		String expiryDate = params.getString("expiryDate");
		
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setExpiryDate(DateUtil.convertSecondsStringToDayStart(expiryDate));
		userInfoDao.updateByKey(userInfo );

		// 参数获取
		ResponseDto<String> rspDto = new ResponseDto<String>();
		return rspDto;
	}


}
