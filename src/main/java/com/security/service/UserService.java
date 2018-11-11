package com.security.service;

import java.util.List;

import com.security.entry.User;

public interface UserService {
	//用户注册
	public int registerUser(User user);
	//用户登录
	public int checkUser(String name,String pwd);
	//用户详情
	public User getUser(User user);
	//获取全部用户
	public List<User> getUserList();
	//统计用户数量
	public int countUser();
}
