package cn.redcdn.jds.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.dao.UserInfoDao;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;

import com.alibaba.fastjson.JSONObject;


@Service("appJIUpdateUser")
public class AppJIUpdateUserService extends BaseService<String> {

    @Autowired
    private TokenApiService tokenApiService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public ResponseDto<String> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {

        CheckUtil.checkEmpty(params, "token");
        tokenApiService.checkUserLoginToken(params.getString("token"));
        String id = tokenApiService.getIdByToken(params.getString("token"));

        UserInfo userInfo = JSONObject.toJavaObject(params, UserInfo.class);
        userInfo.setId(id);
        userInfoDao.updateByKey(userInfo);

        ResponseDto<String> rspDto = new ResponseDto<>();
        return rspDto;
    }
}
