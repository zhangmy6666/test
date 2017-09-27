/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.common.entity;

import java.util.Date;

import cn.redcdn.jds.common.annotation.Now;
import cn.redcdn.jds.common.annotation.UUID;

/**
 *
 * @ClassName: UserInfo
 * @Description: 数据库表user_info对应的entity
 */
public class UserInfo
{
    /**
     * 
     */
    @UUID
    private String id;

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
     * 头像
     */
    private String headUrl;

    /**
     * 登记时间
     */
    @Now
    private Date registerTime;

    /**
     * 试用截至日期
     */
    private Date proDate;

    /**
     * 服务有效时间
     */
    private Date expiryDate;

    /**
     * 注册时间
     */
    @Now
    private Date createTime;

    /**
     * 更新时间
     */
    @Now(type="U")
    private Date updateTime;

    /**
     * 获取
     * @return id 
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置
     * @param id 
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取视讯号
     * @return nube 视讯号
     */
    public String getNube()
    {
         return nube;
    }

    /**
     * 设置视讯号
     * @param nube 视讯号
     */
    public void setNube(String nube)
    {
         this.nube = nube;
    }

    /**
     * 获取昵称
     * @return nickName 昵称
     */
    public String getNickName()
    {
         return nickName;
    }

    /**
     * 设置昵称
     * @param nickName 昵称
     */
    public void setNickName(String nickName)
    {
         this.nickName = nickName;
    }

    /**
     * 获取手机
     * @return mobile 手机
     */
    public String getMobile()
    {
         return mobile;
    }

    /**
     * 设置手机
     * @param mobile 手机
     */
    public void setMobile(String mobile)
    {
         this.mobile = mobile;
    }

    /**
     * 获取邮箱
     * @return mail 邮箱
     */
    public String getMail()
    {
         return mail;
    }

    /**
     * 设置邮箱
     * @param mail 邮箱
     */
    public void setMail(String mail)
    {
         this.mail = mail;
    }

    /**
     * 获取头像
     * @return headUrl 头像
     */
    public String getHeadUrl()
    {
         return headUrl;
    }

    /**
     * 设置头像
     * @param headUrl 头像
     */
    public void setHeadUrl(String headUrl)
    {
         this.headUrl = headUrl;
    }

    /**
     * 获取登记时间
     * @return registerTime 登记时间
     */
    public Date getRegisterTime()
    {
         return registerTime;
    }

    /**
     * 设置登记时间
     * @param registerTime 登记时间
     */
    public void setRegisterTime(Date registerTime)
    {
         this.registerTime = registerTime;
    }

    /**
     * 获取试用截至日期
     * @return proDate 试用截至日期
     */
    public Date getProDate()
    {
         return proDate;
    }

    /**
     * 设置试用截至日期
     * @param proDate 试用截至日期
     */
    public void setProDate(Date proDate)
    {
         this.proDate = proDate;
    }

    /**
     * 获取服务有效时间
     * @return expiryDate 服务有效时间
     */
    public Date getExpiryDate()
    {
         return expiryDate;
    }

    /**
     * 设置服务有效时间
     * @param expiryDate 服务有效时间
     */
    public void setExpiryDate(Date expiryDate)
    {
         this.expiryDate = expiryDate;
    }

    /**
     * 获取注册时间
     * @return createTime 注册时间
     */
    public Date getCreateTime()
    {
         return createTime;
    }

    /**
     * 设置注册时间
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime)
    {
         this.createTime = createTime;
    }

    /**
     * 获取更新时间
     * @return updateTime 更新时间
     */
    public Date getUpdateTime()
    {
         return updateTime;
    }

    /**
     * 设置更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime)
    {
         this.updateTime = updateTime;
    }
}