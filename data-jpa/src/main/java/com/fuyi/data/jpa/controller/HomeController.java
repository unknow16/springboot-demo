package com.fuyi.data.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuyi.data.jpa.entity.User;
import com.fuyi.data.jpa.repo.UserRepository;

@RestController
public class HomeController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/addUser")
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	@DeleteMapping("/deleteUser")
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
