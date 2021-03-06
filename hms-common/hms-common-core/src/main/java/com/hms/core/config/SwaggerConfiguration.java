package com.hms.core.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/20 20:02
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * pro环境：生产环境，面向外部用户的环境，连接上互联网即可访问的正式环境。
     *
     * pre环境：灰度环境，外部用户可以访问，但是服务器配置相对低，其它和生产一样。
     *
     * test环境：测试环境，外部用户无法访问，专门给测试人员使用的，版本相对稳定。
     *
     * dev环境：开发环境，外部用户无法访问，开发人员使用，版本变动很大。
     *
     **/
    private static String  swaggertitle = "酒店管理系统API";

    private String version = "1.0-SNAPSHOT";

    private String license = "Apache License 2.0";

    private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public Docket createWebApi() {

        //在配置好的配置类中增加此段代码即可
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值
        pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//basePackage("com.hms.provider.web.controller")
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)//************把消息头添加
                .enable(true);
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggertitle)
                .description("API接口文档")
                .contact(new Contact("luo", "https://github.com/lshaoshuai/hms-springcloud", ""))
                .version("1.0")
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }
}