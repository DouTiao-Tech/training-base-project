package com.darcytech.training.web;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.darcytech.training.catalog.CatalogRepositoryConfig;
import com.darcytech.training.node.NodeRepositoryConfig;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import({CatalogRepositoryConfig.class, NodeRepositoryConfig.class})
public class WebMain extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WebMain.class, args);

        //        Properties properties = new Properties();
        //        properties.put(PropertyKeyConst.ConsumerId, "CID_jiaoyi");
        //        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
        //        properties.put(PropertyKeyConst.AccessKey, "21598294");
        //        properties.put(PropertyKeyConst.SecretKey, "bda1a9b6d4396740264944003f585129");
        //        Consumer consumer = ONSFactory.createConsumer(properties);
        //        consumer.subscribe("rmq_sys_jst_21598294", null, (message, context) -> {
        //            System.out.println("Receive: " + new String(message.getBody(), Charsets.UTF_8));
        //            return Action.ReconsumeLater;
        //        });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/**/*.htm");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ISO_DATE);
        registrar.setTimeFormatter(DateTimeFormatter.ISO_TIME);
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(registry);
    }

}
