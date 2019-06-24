package com.hms.core.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * @author luoshao
 * @date 2019/6/23 10:08
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@EnableDiscoveryClient  //由于服务发现组件有多种选择，Zookeeper,Consul等，这个注解为各种服务提供了支持
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableSwagger2
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomSpringCloudApplication {
}
