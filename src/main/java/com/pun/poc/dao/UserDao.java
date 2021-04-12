package com.pun.poc.dao;

import com.pun.poc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(@Param("username") String username);
}
