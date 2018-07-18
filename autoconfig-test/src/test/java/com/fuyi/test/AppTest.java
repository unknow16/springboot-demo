package com.fuyi.test;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuyi.boot.BootApplication;
import com.fuyi.starter.FuyiService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootApplication.class})
public class AppTest {

	@Autowired
	private FuyiService fuyiService;
	
	@Test
	public void test1() {
		assertThat(fuyiService.wrap("fuyi")).isEqualTo("www.fuyi.com");
	}
}
