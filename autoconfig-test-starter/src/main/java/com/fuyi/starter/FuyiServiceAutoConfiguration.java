package com.fuyi.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类主要作用是SpringBoot的配置核心，它会写在MEAT-INF/spring.factories中<br>
 * 告知SpringBoot在启动时去读取该类并根据该类的规则进行配置。
 * 
 * @author fuyi
 *
 */
@Configuration
@EnableConfigurationProperties(FuyiProperties.class)
@ConditionalOnClass(FuyiService.class)
@ConditionalOnProperty(prefix = "fuyi.service" , value = "enabled" , matchIfMissing = true)
public class FuyiServiceAutoConfiguration {

	@Autowired
	private FuyiProperties fuyiProperties;
	
	@Bean
	@ConditionalOnMissingBean
	public FuyiService autoConfig() {
		FuyiService fuyiService = new FuyiService(fuyiProperties.getPrefix(), fuyiProperties.getSuffix());
		return fuyiService;
	}
}
