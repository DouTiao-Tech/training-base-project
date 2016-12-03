package com.darcytech.training.core.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darcytech.training.core.catalog.model.User;

public interface UserDao extends JpaRepository<User, Long> {
}
