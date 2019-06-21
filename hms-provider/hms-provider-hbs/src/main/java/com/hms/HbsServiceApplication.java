package com.hms;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient  //由于服务发现组件有多种选择，Zookeeper,Consul等，这个注解为各种服务提供了支持
@SpringBootApplication
@EnableSwagger2
public class HbsServiceApplication {

    public static void main(String[] args) {

        /**
         * 当前项目案例的环境，配置的是eureka集群环境，既然eureka是集群环境，则服务提供者也应该至少有2个实例；
         *
         * 所以，不能使用普通的 SpringApplication.run(EurekaServer.class,args); 来启动项目了；因为我们需要指定不同的端口号来启动项目；
         * 因此，请通过下面的形式来为不同的服务提供者应用指定不同的端口号；
         *
         * 【启动步骤就是，需要运行两次main方法，分别输入不同的端口号，与机器上不冲突即可；这样就实现了2个服务提供者的高可用服务了】
         */

        System.out.println("================================================== 开始启动 hbs 服务 =============================================================");
//        System.out.println("请在控制台指定user服务的端口号 —— [端口号随意指定，注意不要与本机端口号出现冲突即可]");
//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine(); //让用户指定端口号
        new SpringApplicationBuilder(HbsServiceApplication.class).run(args);//启动项目

        System.out.println("================================================== hbs服务启动成功 =============================================================");
    }

}
