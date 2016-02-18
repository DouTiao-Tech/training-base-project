package com.darcytech.training.catalog.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.darcytech.training.catalog.model.Server;

public class ServerDaoJdbc {

    private final JdbcTemplate jdbcTemplate;

    public ServerDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Server> findAll() {
        BeanPropertyRowMapper<Server> mapper = new BeanPropertyRowMapper<>(Server.class);
        return jdbcTemplate.query("select id, db_host, db_port, db_username, db_password from server", mapper);
    }

}
