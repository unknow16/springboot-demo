package com.fuyi.data.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuyi.data.mybatis.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	UserMapper userMapper;

	@Test
	@Rollback
	public void test() {
		userMapper.insert("bbb", 444);
	}
}
