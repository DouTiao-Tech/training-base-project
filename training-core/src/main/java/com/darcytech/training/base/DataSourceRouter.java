package com.darcytech.training.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.validation.BindException;

import com.darcytech.training.catalog.model.Server;
import com.darcytech.training.node.ServerSelector;

public class DataSourceRouter extends AbstractRoutingDataSource implements ServerSelector {

    private final ThreadLocal<Object> selectedServer = new ThreadLocal<>();

    private final List<Server> servers;

    private final String configPrefix;

    @Autowired
    private ConfigurableEnvironment environment;

    public DataSourceRouter(List<Server> servers) {
        this(servers, "datasource.node");
    }

    public DataSourceRouter(List<Server> servers, String configPrefix) {
        this.servers = servers;
        this.configPrefix = configPrefix;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return selectedServer.get();
    }

    @Override
    public void selectServer(Object server) {
        selectedServer.set(server);
    }

    @Override
    public void afterPropertiesSet() {
        if (servers.isEmpty()) {
            throw new IllegalArgumentException("servers属性不能为空或null");
        }
        setTargetDataSources(buildDataSources(servers));
        super.afterPropertiesSet();
    }

    public Map<Object, Object> buildDataSources(List<Server> servers) {
        Map<Object, Object> dataSources = new HashMap<>(servers.size());
        for (Server s : servers) {
            DataSourceBuilder factory = DataSourceBuilder.create()
                    .url(s.getJdbcUrl())
                    .username(s.getUsername())
                    .password(s.getPassword());
            dataSources.put(s.getId(), bindProperties(factory.build()));
        }
        return dataSources;
    }

    private <T> T bindProperties(T target) {
        PropertiesConfigurationFactory<T> binder = new PropertiesConfigurationFactory<>(target);
        binder.setTargetName(configPrefix);
        binder.setConversionService(new DefaultConversionService());
        binder.setPropertySources(environment.getPropertySources());
        try {
            binder.bindPropertiesToTarget();
        } catch (BindException ex) {
            throw new IllegalStateException("Cannot bind to DataSource", ex);
        }
        return target;
    }

}
