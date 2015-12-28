package com.darcytech.training.catalog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darcytech.training.catalog.model.Server;

public interface ServerDao extends JpaRepository<Server, Integer> {
}
