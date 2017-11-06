package com.amhi.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amhi.dao.UserDao;
import com.amhi.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public boolean saveUser(User user) {

		// String password = user.getPassword();
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String hashedPassword = passwordEncoder.encode(password);
		// user.setPassword(hashedPassword);
		logger.info("going to save, now at service level");
		return userDao.saveUser(user);

	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User login(String email, String password) {
		return userDao.login(email, password);

	}

	@Override
	public List<User> getUsers() {

		return userDao.getUsers();
	}

}
