package com.lxjk.datacollection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ziv
 * @Date: 2018/5/17 14:00
 * @Description:RestTemplat配置类
 */
@Configuration
public class RestTemplateconfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        /**单位为ms*/
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }


}
