package com.jackie.dao;

import java.util.List;

import com.jackie.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 获取所有用户信息
     * @return
     */
    List<User> getAllUser();
}