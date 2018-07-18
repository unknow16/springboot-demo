package com.fuyi.cache.ehcache.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "userCache11")
@Service
public class CacheService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@CachePut
	public void save(User user) {
		System.out.println("添加到db");
		jdbcTemplate.update("insert into user (id, name, age) values (?,?,?)", user.getId(), user.getName(), user.getAge());
	}
	
	@CacheEvict
	public void deleteById(Integer id) {
		System.out.println("从db删除, id = " + id);
		//jdbcTemplate.update("delete from user where id = ?", id);
	}

	@CacheEvict(allEntries = true)
	public void deleteAll() {
		System.out.println("删除所有db");
		jdbcTemplate.update("delete from user");
	}
	
	@Cacheable
	public User getUserById(Integer id) {
		System.out.println("从db查询数据，id = " + id);
		return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[] {id}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				return user;
			}
			
		});
	}
	
}
