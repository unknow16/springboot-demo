package com.fuyi.starter;

/**
 * 服务类是指主要的功能类，如果没有SpringBoot，这些服务类在Spring中都是需要自己去配置生成的。
 * <p>
 * 如Mybatis中的DataSource。
 * <p>
 * 这里讲一下我们的Starter要实现的功能，很简单，提供一个Service,<br>
 * 包含一个能够将字符串加上前后缀的方法String wrap(String word)。
 * 
 * @author fuyi
 *
 */
public class FuyiService {
	
	private String prefix;
    private String suffix;

    public FuyiService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
