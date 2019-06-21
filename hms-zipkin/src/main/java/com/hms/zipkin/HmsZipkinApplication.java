package com.hms.zipkin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

/**
 * @author luoshao
 * @date 2019/6/19 15:24
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@EnableEurekaClient
@SpringBootApplication
@EnableZipkinStreamServer
public class HmsZipkinApplication {

    public static void main(String[] args) {
        System.out.println("================================================== 开始启动 zipkin =============================================================");
        new SpringApplicationBuilder(HmsZipkinApplication.class).run(args);
        System.out.println("================================================== zipkin 启动成功 =============================================================");
    }

    @Bean
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
    }
}
