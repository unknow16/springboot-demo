package com.fuyi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuyi.data.jpa.BootApplication;
import com.fuyi.data.jpa.entity.User;
import com.fuyi.data.jpa.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment  = WebEnvironment.RANDOM_PORT, classes = BootApplication.class)
public class TestApp {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void test1() {
		User user = new User();
		user.setName("fuyi");
		user.setAge(123);
		userRepository.save(user);
	}
}
