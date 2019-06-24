package com.hms.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Dashboard仪表盘
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        System.out.println("================================================== 开始启动 hystix =============================================================");
        SpringApplication.run(HystrixDashboardApplication.class, args);
        System.out.println("================================================== hystix 服务启动成功 =============================================================");
    }
}
