package com.lxjk.datareceive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DatareceiveApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DatareceiveApplication.class, args);
    }
    /**
     * 部署war包的时候使用
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }

    /**
     * api文档配置   localhost:8080/swagger-ui.html
     * @return
     */
    @Bean
    public Docket createRestApi() {
//
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lxjk.datareceive"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 文档的部分描述
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("搅拌站系统接口文档 v1.0")
                //描述
                .description("更多相关内容请联系：mail   ：  2215699167@qq.com ")
                //版本
                .version("1.0")
                .build();
    }

}
