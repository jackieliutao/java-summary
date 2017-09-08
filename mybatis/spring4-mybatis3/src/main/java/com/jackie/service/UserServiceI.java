package com.jackie.service;

import java.util.List;

import com.jackie.domain.User;

public interface UserServiceI {
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 根据用户id获取用户
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();

}
