package com.fuyi.actuator.endpoint;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "endpoints.servertime")
public class ServerTimeEndpoint extends AbstractEndpoint<Map<String, Object>> {

	public ServerTimeEndpoint() {
		super("servertime", false);
	}

	@Override
	public Map<String, Object> invoke() {
		Map<String, Object> result = new HashMap<String, Object>();
        
		LocalDateTime now = LocalDateTime.now();
        result.put("server_time", now.toLocalTime());
        result.put("server_date", now.toLocalDate());
        return result;
	}

}
