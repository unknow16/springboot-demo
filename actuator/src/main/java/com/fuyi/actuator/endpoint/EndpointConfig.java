package com.fuyi.actuator.endpoint;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

	@Bean
	public static Endpoint<Map<String, Object>> servertime() {
		return new ServerTimeEndpoint();
	}
}
