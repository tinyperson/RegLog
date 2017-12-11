package com.reglog.service;


import com.reglog.dao.UserDao;
import com.reglog.domain.User;



public class UserService {

	public static final int USERNAME_EXIST = 1;
	public static final int EMAIL_EXIST = 2;
	public static final int SUCCESS = 3;

	public int regist(User user) {
		UserDao userDao = new UserDao();
		User RegUsername = userDao.selectOneByUsername(user);
		User RegEmail = userDao.selectOneByEmail(user);
		
		if (RegUsername!=null) {
			return this.USERNAME_EXIST;
		}
		if (RegEmail!=null) {
			return this.EMAIL_EXIST;
		}
		
		userDao.insert(user);
		return this.SUCCESS;
	}
	
	public User login(User user) {
		
		UserDao userDao = new UserDao();
		return userDao.selectOne(user);
	}

}
