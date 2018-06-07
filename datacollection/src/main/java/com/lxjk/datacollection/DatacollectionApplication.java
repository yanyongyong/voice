package com.lxjk.datacollection;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("com.lxjk.datacollection.data.repository")
public class DatacollectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatacollectionApplication.class, args);
    }
}
