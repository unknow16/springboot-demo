package com.fuyi.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuyi.cache.redis.service.CacheService;
import com.fuyi.cache.redis.service.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	CacheService cacheService;
	
	@Autowired
	CacheManager cacheManager;
	
	@Test
	public void test0() {
		System.out.println("-----------> cacheManager = " + cacheManager);
	}


	@Rollback
	@Test
	public void test() {
		Integer id = 43;
		
		User user = new User();
		user.setId(id);
		user.setName("fuyi");
		user.setAge(22);
		cacheService.save(user); // db
		
		User u = cacheService.getUserById(id); // db
		System.out.println("result == " + u);
		
		User u1 = cacheService.getUserById(id); // no db
		System.out.println("result == " + u1);
		
		cacheService.deleteById(id); // delete cache, no delete data
		
		User u2 = cacheService.getUserById(id); // db
		System.out.println("result == " + u2);
		
		User u3 = cacheService.getUserById(id); // no db
		System.out.println("result == " + u3);
	}
}
