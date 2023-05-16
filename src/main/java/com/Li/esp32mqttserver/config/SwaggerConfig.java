package com.Li.esp32mqttserver.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30).apiInfo(
                new ApiInfoBuilder()
                        .contact(new Contact("Lijjsk","","2609478776@qq.com"))
                        .title("电子墨水屏商品标签管理系统")
                        .build()
        );
    }
}
