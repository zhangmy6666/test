/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.common.dao;

import cn.redcdn.jds.common.entity.UserInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserInfoDao
{
    /**
     * 将数据插入数据库表中
     * 
     * @param userInfo 插入的数据信息
     */
    void insert(UserInfo userInfo);

    /**
     * 根据key查找数据库中信息
     * 
     * @param String id 查询的数据信息条件
     * @return UserInfo 符合条件的数据信息
     */
    UserInfo queryByKey(String id);

    /**
     * 根据key更新数据库中信息
     * 
     * @param userInfo 更新的数据信息
     * @return 更新数据的行数
     */
    int updateByKey(UserInfo userInfo);

    /**
     * 根据key删除数据库中信息
     * 
     * @param String id 删除的数据信息
     * @return 删除数据的行数
     */
    int deleteByKey(String id);
    /**
     * 根据field查询据库中信息
     * 
     * @param String fieldName 查询的字段名信息
     * @param String fieldValue 查询的字段的数据信息
     * @return List<UserInfo> 符合条件的数据信息
     */
    List<UserInfo> queryByField(@Param("fieldName") String fieldName, @Param("fieldValue") String fieldValue);

}