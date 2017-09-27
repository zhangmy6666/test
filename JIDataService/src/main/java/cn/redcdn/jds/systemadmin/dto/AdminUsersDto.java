/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.systemadmin.dto;

import java.util.List;

import cn.redcdn.jds.common.dto.PageResultDto;

public class AdminUsersDto extends PageResultDto {
	/**
	 * 用户List
	 */
	private List<UsersDto> userList;

	public List<UsersDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UsersDto> userList) {
		this.userList = userList;
	}
}