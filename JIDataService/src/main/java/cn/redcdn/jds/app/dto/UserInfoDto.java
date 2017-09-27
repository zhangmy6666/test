/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.app.dto;

import java.util.Date;


/**
 *
 * @ClassName: UserInfoDto
 * @Description: reponse返回数据包装用
 */
public class UserInfoDto
{
    /**
     * token
     */
    private String token;
    
    /**
     * 视讯号
     */
    private String nube;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户类型
     */
    private String userType;
    
    /**
     * 头像
     */
    private String headUrl;

    /**
     * 服务有效时间
     */
    private Date expiryDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNube() {
		return nube;
	}

	public void setNube(String nube) {
		this.nube = nube;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}