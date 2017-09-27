package cn.redcdn.jds.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.redcdn.jds.app.dao.AppJIUsersDao;
import cn.redcdn.jds.app.dto.AppJISearchUsersDto;
import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.api.TypeApiService;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Service("appJISearchUsers")
public class AppJISearchUsersService extends BaseService<List<AppJISearchUsersDto>> {

    @Autowired
    private TokenApiService tokenApiService;

    @Autowired
    private AppJIUsersDao appJIUsersDao;

    @Autowired
    private TypeApiService typeApiService;

    @Override
    public ResponseDto<List<AppJISearchUsersDto>> process(JSONObject params, HttpServletRequest request, HttpServletResponse response) {

        CheckUtil.checkEmpty(params, "token", "searchType", "content");

        tokenApiService.checkUserLoginToken(params.getString("token"));

        String searchType = params.getString("searchType");
        JSONArray contents = params.getJSONArray("content");

        ResponseDto<List<AppJISearchUsersDto>> rspDto = new ResponseDto<>();
        List<AppJISearchUsersDto> returnUserInfos = new ArrayList<>();
        List<UserInfo> userInfos = appJIUsersDao.queryByField(searchType, contents);

        if (userInfos.size() > 0) {
            for (UserInfo userInfo : userInfos) {
                AppJISearchUsersDto appJISearchUsersDto = new AppJISearchUsersDto();
                BeanUtils.copyProperties(userInfo, appJISearchUsersDto);
                appJISearchUsersDto.setUserType(typeApiService.getUserType(userInfo.getProDate(), userInfo.getExpiryDate()));
                returnUserInfos.add(appJISearchUsersDto);
            }
        }
        rspDto.setData(returnUserInfos);
        return rspDto;
    }
}
