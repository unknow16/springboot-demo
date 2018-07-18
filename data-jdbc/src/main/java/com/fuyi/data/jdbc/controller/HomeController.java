package com.fuyi.data.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fuyi.data.jdbc.service.UserService;

@RestController
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public Object getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/create/{userId:\\d+}", method = RequestMethod.GET)
	public void create(@PathVariable Integer userId) {
		userService.create("aaa", userId);
	}
}
