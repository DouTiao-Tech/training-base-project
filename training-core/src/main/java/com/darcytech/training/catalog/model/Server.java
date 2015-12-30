package com.darcytech.training.catalog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.darcytech.training.base.BaseJpaModel;
import com.darcytech.training.base.JdbcServer;

@Entity
public class Server extends BaseJpaModel<Integer> implements JdbcServer {

    @Id
    private Integer id;

    private String dbHost;

    private int dbPort;

    private String dbName;

    private String dbUsername;

    private String dbPassword;

    public Server() {
        // used by spring jdbc
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

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getJdbcUrl() {
        return "jdbc:mysql://" + getDbHost() + ":" + getDbPort() + "/" + getDbName();
    }

    @Override
    public String toString() {
        return getJdbcUrl();
    }

    @Override
    public String getUrl() {
        return getJdbcUrl();
    }

    @Override
    public String getUsername() {
        return getDbUsername();
    }

    @Override
    public String getPassword() {
        return getDbPassword();
    }

}
