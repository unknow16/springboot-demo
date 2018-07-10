package com.fuyi.actuator.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = check();
		if(errorCode != 0) {
			return Health.down()
						.withDetail("status", errorCode)
						.withDetail("message", "服务器故障")
						.build();
		}
		return Health.up().build();
	}

	/*
	 * 检测状态
	 */
	private int check() {
		return HttpStatus.NOT_FOUND.value();
	}

}
