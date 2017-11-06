package com.amhi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amhi.model.Response;
import com.amhi.model.User;
import com.amhi.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MyController {

	@Autowired
	private UserService userService;
	final static Logger logger = Logger.getLogger(MyController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		logger.debug("going to Save ....");

		Response response = null;
		User availUser = userService.getUserByEmail(user.getEmail());
		if (availUser != null) {
			logger.info("User Exist already with this Email");
			response = new Response();
			response.setMessage("User Exist already with this Email");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

		boolean saveUser = userService.saveUser(user);
		if (!saveUser) {
			logger.info("Something Went Wrong");
			response = new Response();
			response.setMessage("Something Went Wrong");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		logger.info("Register Successfully");
		response = new Response();
		response.setMessage("Register Successfully");

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody User user) {

		Response response = null;
		User loginSuccess = null;
		User availUser = userService.getUserByEmail(user.getEmail());
		if (availUser != null) {

			loginSuccess = userService.login(user.getEmail(),
					user.getPassword());
			if (loginSuccess == null) {
				response = new Response();
				response.setMessage("username or password is incorrect??");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
			response = new Response();
			response.setMessage("Login Successfully");
		} else {
			response = new Response();
			response.setMessage("User Doesn't Exit with this Email.");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(loginSuccess, HttpStatus.OK);

	}

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers() {

		Response response = null;
		List<User> users = userService.getUsers();

		if (users == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(users, HttpStatus.OK);
	}

	@RequestMapping(value = {"/user/{email}","/"}, method = RequestMethod.GET)
	public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {

		Response response = null;
		User availUser = userService.getUserByEmail(email);

		if (availUser == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(availUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserEmail(@RequestParam String email) {

		Response response = null;
		User availUser = userService.getUserByEmail(email);

		if (availUser == null) {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(availUser, HttpStatus.OK);
	}

}
