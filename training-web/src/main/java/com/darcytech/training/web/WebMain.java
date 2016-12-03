package com.darcytech.training.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.darcytech.training.core.catalog.CatalogRepositoryConfig;
import com.darcytech.training.core.top.TopConfig;
import com.darcytech.training.web.security.AuthenticatedUserArgumentResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import({CatalogRepositoryConfig.class, TopConfig.class})
public class WebMain extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerCustomizer customizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof JettyEmbeddedServletContainerFactory) {
                    customizeJetty((JettyEmbeddedServletContainerFactory) container);
                }
            }

            private void customizeJetty(JettyEmbeddedServletContainerFactory jetty) {
                jetty.setThreadPool(new QueuedThreadPool(6, 4));
            }
        };

    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WebMain.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticatedUserArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ISO_DATE);
        registrar.setTimeFormatter(DateTimeFormatter.ISO_TIME);
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(registry);
    }

    /**
     * javaTimeModule 会被spring boot 自动扫描到，这里生成一个新的module用于修改缺省的LocalDateTime和LocalDate的格式
     */
    @Bean
    public SimpleModule jsr310JacksonModule() {
        SimpleModule jsr310Module = new SimpleModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        jsr310Module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        jsr310Module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));

        // 虽然可以通过配置 spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS = false 来达到同样的目的
        // 写在这里可以更明确些
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        jsr310Module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        jsr310Module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));

        return jsr310Module;
    }

}
