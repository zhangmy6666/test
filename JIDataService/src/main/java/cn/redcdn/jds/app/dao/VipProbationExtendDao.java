/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.app.dao;

import java.util.List;

import cn.redcdn.jds.common.entity.VipProbation;

public interface VipProbationExtendDao
{
    /**
     * 查找表里面全部信息
     * 
     * @return VipProbation 数据信息
     */
    List<VipProbation> queryAll();
}