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
 * @ClassName: VipProbation
 * @Description: 数据库表vip_probation对应的entity
 */
public class VipProbation
{
    /**
     * 
     */
    @UUID
    private String id;

    /**
     * 试用期设置模式
     */
    private Integer proMode;

    /**
     * 试用天数
     */
    private Integer proDay;

    /**
     * 试用截至日期
     */
    private Date proDate;

    /**
     * 创建时间
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
     * 获取试用期设置模式
     * @return proMode 试用期设置模式
     */
    public Integer getProMode()
    {
         return proMode;
    }

    /**
     * 设置试用期设置模式
     * @param proMode 试用期设置模式
     */
    public void setProMode(Integer proMode)
    {
         this.proMode = proMode;
    }

    /**
     * 获取试用天数
     * @return proDay 试用天数
     */
    public Integer getProDay()
    {
         return proDay;
    }

    /**
     * 设置试用天数
     * @param proDay 试用天数
     */
    public void setProDay(Integer proDay)
    {
         this.proDay = proDay;
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
     * 获取创建时间
     * @return createTime 创建时间
     */
    public Date getCreateTime()
    {
         return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
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