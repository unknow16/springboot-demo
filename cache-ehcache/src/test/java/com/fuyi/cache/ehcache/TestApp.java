package com.fuyi.cache.ehcache;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuyi.cache.ehcache.service.CacheService;
import com.fuyi.cache.ehcache.service.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApp {
	
	@Autowired
	CacheService cacheService;
	
	@Autowired
	org.springframework.cache.CacheManager cacheManager;
	
	@Test
	public void test0() {
		System.out.println("-----------> cacheManager = " + cacheManager);
	}


	@Rollback
	@Test
	public void test1() {
		Integer id = 47;
		
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
	
	
	@Test
	public void test() throws Exception {
		// 创建底层缓存实现ehcache
		net.sf.ehcache.CacheManager ehcacheCacheManager = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
	
		// 创建spring中抽象的CacheManager
		EhCacheCacheManager ehcacheCacheManagerInSpring = new EhCacheCacheManager();
		ehcacheCacheManagerInSpring.setCacheManager(ehcacheCacheManager);
	
		Cache cache = ehcacheCacheManagerInSpring.getCache("userCache");
		
		cache.put("1001", "name=fuyi");
		
		assertThat(cache.get("1001")).isEqualTo("name=fuyi");
	}
}
