package com.darcytech.training.core.node;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.darcytech.training.core.base.DataSourceRouter;
import com.darcytech.training.core.base.EnhancedJpaRepository;
import com.darcytech.training.core.catalog.CatalogRepositoryConfig;
import com.darcytech.training.core.catalog.model.Server;
import com.darcytech.training.core.node.model.Trade;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        entityManagerFactoryRef = "nodeEntityManagerFactory",
        transactionManagerRef = NodeRepositoryConfig.TX_MANAGER_NAME,
        repositoryBaseClass = EnhancedJpaRepository.class
)
public class NodeRepositoryConfig {

    public static final String UNIT_NAME = "node";

    public static final String TX_MANAGER_NAME = "nodeTransactionManager";

    @Qualifier(CatalogRepositoryConfig.UNIT_NAME)
    @Autowired
    private DataSource catalogDataSource;

    @Bean
    @ConditionalOnMissingBean(name = "nodeDataSource")
    @Qualifier(UNIT_NAME)
    public DataSource nodeDataSource() {
        return new DataSourceRouter(findAll());
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

    private List<Server> findAll() {
        BeanPropertyRowMapper<Server> mapper = new BeanPropertyRowMapper<>(Server.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(catalogDataSource);
        return jdbcTemplate.query("select id, db_host, db_port, db_username, db_password from server", mapper);
    }

}
