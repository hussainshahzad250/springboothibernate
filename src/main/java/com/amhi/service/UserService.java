package com.amhi.service;

import java.util.List;

import com.amhi.model.User;
import com.amhi.model.Users;

public interface UserService {

	boolean saveUser(User user);

	User getUserByEmail(String email);

	User login(String email, String password);

	List<User> getUsers();

	

	boolean deletetUserById(String id);

	boolean saveHeros(Users users);

}
