package com.hms;

import com.hms.core.annotation.CustomSpringCloudApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@SpringBootApplication
//@EnableSwagger2
@CustomSpringCloudApplication
public class ManagerServiceApplication {
    public static void main(String[] args) {
        System.out.println("================================================== 开始启动 manager 服务 =============================================================");
        new SpringApplicationBuilder(ManagerServiceApplication.class).run(args);//启动项目
        System.out.println("================================================== manager 服务启动成功 =============================================================");
    }
}
