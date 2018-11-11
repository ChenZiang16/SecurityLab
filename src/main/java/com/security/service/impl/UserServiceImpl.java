package com.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.UserDao;
import com.security.entry.User;
import com.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public int registerUser(User user) {
		int flag = userDao.addUser(user);
		return flag;
	}

	@Override
	public int checkUser(String name, String pwd) {
		User user = new User();
		user.setName(name);
		user.setPassword(pwd);
		return userDao.checkUser(user);
	}

	@Override
	public User getUser(User user) {
		return userDao.getUser(user);
	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public int countUser() {
		return userDao.countUser();
	}

}
