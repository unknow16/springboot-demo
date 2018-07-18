package com.fuyi.data.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fuyi.data.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from User where id = :id")
	User findUserById(@Param(value = "id") Long id);
}
