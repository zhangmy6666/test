package cn.redcdn.jds.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.redcdn.jds.common.entity.UserInfo;

import com.alibaba.fastjson.JSONArray;

/**
 * Project: JIDataService
 * Package: cn.redcdn.jds.app.dao
 * Author: mason
 * Time: 13:53
 * Date: 2017-09-25
 * Created with IntelliJ IDEA
 */
public interface AppJIUsersDao {

    List<UserInfo> queryByField(@Param("searchType") String searchType, @Param("contents") JSONArray contents);
}