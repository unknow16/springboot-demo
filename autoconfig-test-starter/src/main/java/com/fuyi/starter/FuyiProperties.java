package com.fuyi.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 这个类定义了默认的属性值，如该类中，只有一个属性值msg，默认为world
 * <p>
 * {@ConfigurationProperties} 注解会定义一个前缀匹配，如果想修改属性值，<br>
 * 可以在application.properties中使用“前缀.属性=修改的值”进行修改。<br>
 * 
 * @author fuyi
 *
 */
@ConfigurationProperties(prefix = "fuyi.service")
public class FuyiProperties {

	private String prefix = "www.";
    private String suffix = ".com";
    
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
