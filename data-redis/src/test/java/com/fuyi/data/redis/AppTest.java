package com.fuyi.data.redis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.fuyi.data.redis.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	public void test() {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set("fuyi", "haha");

		String result = opsForValue.get("fuyi");
		assertThat(result).isEqualTo("haha");
	}

	@Test
	public void test1() {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		
		User user = new User("fuyi", 123);
		opsForValue.set("aaa", user);
		
		User result = (User)opsForValue.get("aaa");
		assertThat(result.getName()).isEqualTo("fuyi");
		assertThat(result.getAge()).isEqualTo(123);
	}
	
	@Test
	public void test2() {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		User user = new User("fuyi1123", 41123);
		String userStr = JSONObject.toJSONString(user);
		//opsForHash.put("sjd:intercept:age", "2000000012:2013323122344", userStr);
		opsForHash.put("sjd:intercept:apply", "2000000012:2013323122344", userStr);
		
	}
	
}
