package com.amhi.service;

import java.util.List;

import com.amhi.model.User;

public interface UserService {

	boolean saveUser(User user);

	User getUserByEmail(String email);

	User login(String email, String password);

	List<User> getUsers();

}
