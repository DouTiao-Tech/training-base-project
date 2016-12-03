package com.darcytech.training.updater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.darcytech.training.core.catalog.CatalogRepositoryConfig;
import com.darcytech.training.updater.scheduler.StartAsyncScheduler;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {

    public static void main(String[] args) {
        Object[] configClasses = {Main.class, CatalogRepositoryConfig.class};
        ApplicationContext context = SpringApplication.run(configClasses, args);

        context.getBean(StartAsyncScheduler.class).startAsync();
    }

}
