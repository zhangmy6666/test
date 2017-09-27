package cn.redcdn.jds.systemadmin.dao;

import org.apache.ibatis.annotations.Param;

import cn.redcdn.jds.common.entity.SystemAdmin;

public interface AdminLoginDao {

	/**
     * 查询用户
     * 
     * @param String account 账号
     * @param String password 密码
     * @return 信息
     */
	SystemAdmin getSystemAdminInfo(@Param("account")String account, @Param("password") String password);
}
