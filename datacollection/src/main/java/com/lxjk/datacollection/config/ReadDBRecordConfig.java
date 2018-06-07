package com.lxjk.datacollection.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: ziv
 * @Date: 2018/5/24 15:06
 * @Description:
 */
//@Data
//@Component
//@ConfigurationProperties
//@PropertySource("classpath:ReadDBRecord.yml")
public class ReadDBRecordConfig {

    private String time;

}
