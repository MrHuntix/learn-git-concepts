package com.pun.poc.dao;

import com.pun.poc.models.Gym;
import com.pun.poc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface GymDao extends JpaRepository<Gym, Integer> {
}
