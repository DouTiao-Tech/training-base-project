package com.darcytech.training.core.catalog;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.darcytech.training.core.base.EnhancedJpaRepository;
import com.darcytech.training.core.base.EnhancedJpaRepositoryFactoryBean;
import com.darcytech.training.core.catalog.model.Server;
import com.darcytech.training.core.catalog.service.CustomerService;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        entityManagerFactoryRef = "catalogEntityManagerFactory",
        transactionManagerRef = CatalogRepositoryConfig.TX_MANAGER_NAME,
        repositoryBaseClass = EnhancedJpaRepository.class,
        repositoryFactoryBeanClass = EnhancedJpaRepositoryFactoryBean.class
)
@ComponentScan(basePackageClasses = CustomerService.class)
public class CatalogRepositoryConfig {

    public static final String UNIT_NAME = "catalog";

    public static final String TX_MANAGER_NAME = "catalogTransactionManager";

    @Bean
    @ConditionalOnMissingBean(name = "catalogDataSource")
    @ConfigurationProperties(prefix = "datasource.catalog")
    @Qualifier(UNIT_NAME)
    @Primary
    public DataSource catalogDataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    @Qualifier(UNIT_NAME)
    @Primary
    public PlatformTransactionManager catalogTransactionManager(@Qualifier(UNIT_NAME) DataSource catalogDataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setPersistenceUnitName(UNIT_NAME);
        return transactionManager;
    }

    @Bean
    @Qualifier(UNIT_NAME)
    @Primary
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

