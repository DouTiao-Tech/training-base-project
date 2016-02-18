package com.darcytech.training;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.darcytech.training.catalog.CatalogRepositoryConfig;
import com.darcytech.training.node.NodeRepositoryConfig;

@Configuration
@EnableAutoConfiguration
@Import({CatalogRepositoryConfig.class, NodeRepositoryConfig.class})
public class DaoTestConfig {

    private EmbeddedDatabaseType type = EmbeddedDatabaseConnection.get(getClass().getClassLoader()).getType();

    @Bean(destroyMethod = "shutdown")
    @Qualifier(CatalogRepositoryConfig.UNIT_NAME)
    @Primary
    public DataSource catalogDataSource() {
        return new EmbeddedDatabaseBuilder().setType(type).build();
    }

    @Bean(destroyMethod = "shutdown")
    @Qualifier(NodeRepositoryConfig.UNIT_NAME)
    public DataSource nodeDataSource() {
        return new EmbeddedDatabaseBuilder().setType(type).build();
    }

}
