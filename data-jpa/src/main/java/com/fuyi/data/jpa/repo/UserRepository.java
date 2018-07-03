package com.fuyi.data.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fuyi.data.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
