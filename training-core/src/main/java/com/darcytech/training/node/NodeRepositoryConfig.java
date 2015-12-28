package com.darcytech.training.node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

import com.darcytech.training.catalog.dao.ServerDao;
import com.darcytech.training.catalog.model.Server;
import com.darcytech.training.node.model.Trade;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = NodeRepositoryConfig.EMF_NAME,
        transactionManagerRef = NodeRepositoryConfig.TX_MANAGER_NAME)
public class NodeRepositoryConfig {

    public static final String UNIT_NAME = "node";

    public static final String TX_MANAGER_NAME = "nodeTransactionManager";

    public static final String EMF_NAME = "nodeEntityManagerFactory";

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private ConfigurableEnvironment environment;

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean(name = "nodeDataSource")
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    public DataSource nodeDataSource() {
        return new NodeDataSourceRouter(buildNodeDataSources(serverDao.findAll()));
    }

    public Map<Object, Object> buildNodeDataSources(List<Server> servers) {
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
        binder.setTargetName("node.datasource");
        binder.setConversionService(new DefaultConversionService());
        binder.setPropertySources(environment.getPropertySources());
        try {
            binder.bindPropertiesToTarget();
        } catch (BindException ex) {
            throw new IllegalStateException("Cannot bind to DataSource", ex);
        }
        return target;
    }

    @Bean
    public PlatformTransactionManager nodeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean nodeEntityManagerFactory(
            @Qualifier(NodeRepositoryConfig.UNIT_NAME) DataSource catalogDataSource,
            EntityManagerFactoryBuilder builder,
            JpaProperties jpaProperties) {
        return builder.dataSource(catalogDataSource)
                .packages(Trade.class, Jsr310JpaConverters.class)
                .persistenceUnit(UNIT_NAME)
                .properties(jpaProperties.getHibernateProperties(catalogDataSource))
                .build();
    }

}
