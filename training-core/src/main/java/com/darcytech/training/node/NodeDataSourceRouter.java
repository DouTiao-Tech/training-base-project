package com.darcytech.training.node;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class NodeDataSourceRouter extends AbstractRoutingDataSource implements ServerSelector {

    private final ThreadLocal<Object> selectedServer = new ThreadLocal<>();

    public NodeDataSourceRouter(Map<Object, Object> targetDataSources) {
        setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return selectedServer.get();
    }

    @Override
    public void selectServer(Object server) {
        selectedServer.set(server);
    }

}
