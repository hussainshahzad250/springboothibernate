package com.amhi.dao;

import java.util.List;

import com.amhi.model.User;

public interface UserDao {

	boolean saveUser(User user);

	User getUserByEmail(String email);

	User login(String email, String password);

	List<User> getUsers();

}
