package cn.redcdn.jds.systemadmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.redcdn.jds.common.api.TokenApiService;
import cn.redcdn.jds.common.api.TypeApiService;
import cn.redcdn.jds.common.dto.ResponseDto;
import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.common.service.BaseService;
import cn.redcdn.jds.common.util.CheckUtil;
import cn.redcdn.jds.systemadmin.dao.AdminUserInfoExtendDao;
import cn.redcdn.jds.systemadmin.dto.AdminUsersDto;
import cn.redcdn.jds.systemadmin.dto.UsersDto;
import cn.redcdn.jds.systemadmin.dto.UsersPageDto;

@Service("adminGetUsers")
public class AdminGetUsersService extends BaseService<AdminUsersDto> {
	
	/**
	 * 用户信息
	 */
	@Autowired
	private AdminUserInfoExtendDao adminUserInfoDao;	
	
	@Autowired
	private TokenApiService tokenApiService;
	
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
    public ResponseDto<AdminUsersDto> process(JSONObject params, 
        HttpServletRequest request, HttpServletResponse response)
    {
    	// 必须check
    	CheckUtil.checkEmpty(params, "token", "userType", "pageNo", "pageSize");
    	
    	// token校验
    	String token = params.getString("token");
    	tokenApiService.checkSystemLoginToken(token);
    	
    	UsersPageDto pageDto = JSONObject.parseObject(params.toJSONString(), UsersPageDto.class);
    	// 参数获取        
    	ResponseDto<AdminUsersDto> rspDto = new ResponseDto<AdminUsersDto>();
        AdminUsersDto adminGetUsersDto = new AdminUsersDto();
        List<UsersDto> userList = new ArrayList<UsersDto>();
        List<UserInfo> userInfoList = adminUserInfoDao.getUsersListWithPage(pageDto);
        for (UserInfo user : userInfoList) {
        	UsersDto dto = new UsersDto();
        	BeanUtils.copyProperties(user, dto);
        	dto.setUserType(typeApiService.getUserType(user.getProDate(), user.getExpiryDate()));
        	userList.add(dto);
        }
        adminGetUsersDto.setUserList(userList);
        adminGetUsersDto.setTotalSize(pageDto.getTotalSize());
        rspDto.setData(adminGetUsersDto);
        
        return rspDto;
    }
}
