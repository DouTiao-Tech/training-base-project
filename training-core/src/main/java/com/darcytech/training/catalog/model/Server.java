package com.darcytech.training.catalog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.darcytech.training.base.BaseJpaModel;

@Entity
public class Server extends BaseJpaModel<Integer> {

    @Id
    private Integer id;

    private String host;

    private int port;

    private String database;

    private String username;

    private String password;

    private Server() {
        // used by hibernate
    }

    public Server(Integer id) {
        setId(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    @Override
    public String toString() {
        return getJdbcUrl();
    }

}
