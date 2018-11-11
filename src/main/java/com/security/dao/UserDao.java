package com.security.dao;

import java.util.List;

import com.security.entry.User;
public interface UserDao {
	
	int addUser(User user);
	
	public int checkUser(User user);
	
	public User getUser(User user);
	
	public List<User> getUserList();
	
	public int countUser();

}
