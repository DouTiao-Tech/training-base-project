package com.darcytech.training.node;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.darcytech.training.base.DataSourceRouter;
import com.darcytech.training.catalog.dao.ServerDao;
import com.darcytech.training.node.model.Trade;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "nodeEntityManagerFactory",
        transactionManagerRef = NodeRepositoryConfig.TX_MANAGER_NAME)
public class NodeRepositoryConfig {

    public static final String UNIT_NAME = "node";

    public static final String TX_MANAGER_NAME = "nodeTransactionManager";

    @Autowired
    private ServerDao serverDao;

    @Bean
    @ConditionalOnMissingBean(name = "nodeDataSource")
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    public DataSource nodeDataSource() {
        return new DataSourceRouter(serverDao.findAll());
    }

    @Bean
    public PlatformTransactionManager nodeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean nodeEntityManagerFactory(
            @Qualifier(UNIT_NAME) DataSource nodeDataSource,
            EntityManagerFactoryBuilder builder,
            JpaProperties jpaProperties) {
        return builder.dataSource(nodeDataSource)
                .packages(Trade.class, Jsr310JpaConverters.class)
                .persistenceUnit(UNIT_NAME)
                .properties(jpaProperties.getHibernateProperties(nodeDataSource))
                .build();
    }

    @Bean
    @Qualifier(UNIT_NAME)
    public JdbcTemplate nodeJdbcTemplate(@Qualifier(UNIT_NAME) DataSource nodeDataSource) {
        return new JdbcTemplate(nodeDataSource);
    }

}
