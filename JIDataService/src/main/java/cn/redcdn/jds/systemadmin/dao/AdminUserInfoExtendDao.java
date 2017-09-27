package cn.redcdn.jds.systemadmin.dao;

import java.util.List;

import cn.redcdn.jds.common.entity.UserInfo;
import cn.redcdn.jds.systemadmin.dto.SearchUserPageDto;
import cn.redcdn.jds.systemadmin.dto.UsersPageDto;

public interface AdminUserInfoExtendDao {

	/**
     * 分页查询
     * 
     * @param pageDto　查询条件
     * @return 查询结果
     */
	List<UserInfo> getUsersListWithPage(UsersPageDto pageDto);
	
	/**
     * 分页查询
     * 
     * @param pageDto　查询条件
     * @return 查询结果
     */
	List<UserInfo> searchUsersListWithPage(SearchUserPageDto pageDto);
}
