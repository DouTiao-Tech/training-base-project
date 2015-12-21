package com.darcytech.training.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darcytech.training.catalog.model.User;

public interface UserDao extends JpaRepository<User, Long> {
}
