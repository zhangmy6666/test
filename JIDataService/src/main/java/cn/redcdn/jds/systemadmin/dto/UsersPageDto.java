package cn.redcdn.jds.systemadmin.dto;

import cn.redcdn.jds.common.dto.PageInfoDto;

public class UsersPageDto extends PageInfoDto
{
	 /**
     * 用户状态类型
     */
    private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
