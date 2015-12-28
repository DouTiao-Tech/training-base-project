package com.darcytech.training.catalog;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.darcytech.training.catalog.model.Server;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = CatalogRepositoryConfig.EMF_NAME,
        transactionManagerRef = CatalogRepositoryConfig.TX_MANAGER_NAME
)
public class CatalogRepositoryConfig {

    public static final String UNIT_NAME = "catalog";

    public static final String TX_MANAGER_NAME = "catalogTransactionManager";

    public static final String EMF_NAME = "catalogEntityManagerFactory";

    @Bean
    @ConditionalOnMissingBean(name = "catalogDataSource")
    @Qualifier(CatalogRepositoryConfig.UNIT_NAME)
    @ConfigurationProperties(prefix = "catalog.datasource")
    @Primary
    public DataSource catalogDataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public PlatformTransactionManager catalogTransactionManager(@Qualifier("catalog") DataSource catalogDataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean catalogEntityManagerFactory(
            @Qualifier(CatalogRepositoryConfig.UNIT_NAME) DataSource catalogDataSource,
            EntityManagerFactoryBuilder builder, JpaProperties jpaProperties) {
        return builder.dataSource(catalogDataSource)
                .packages(Server.class, Jsr310JpaConverters.class)
                .persistenceUnit(UNIT_NAME)
                .properties(jpaProperties.getHibernateProperties(catalogDataSource))
                .build();
    }

}

