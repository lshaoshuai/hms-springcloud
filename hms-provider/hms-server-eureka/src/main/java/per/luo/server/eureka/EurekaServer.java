package per.luo.server.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

/**
 * eureka服务器
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String[] args) {
        /**
         * 由于当前案例环境，已经配置为eureka集群环境，既然是集群环境，则erueka服务器需要至少启动2个服务；
         * 在application.yml文件中已经配置了2台eureka服务器的配置，则在启动的时候，需要分别启动这2个eureka服务器；
         * 所以，就不能使用普通的 SpringApplication.run(EurekaServer.class,args); 来启动项目了；
         * 请通过下面的形式来指定你需要启动的eureka服务器是哪一个；需要使用到 SpringApplicationBuilder 类。该类对SpringApplication进行了封装；
         *
         * 【在控制台输入的时候，请输入你在application.yml中配置的 spring.profiles 的值（yml中配置的值分别是 slave1、slave2），代表着不同的eurek服务器；】
         * 【启动步骤就是，需要运行两次main方法，分别输入 slave1、slave2 即可启动2个eureka服务器】
         */
        System.out.println("============================================================= 开始启动eureka服务 =============================================================");
        System.out.println("请在控制台输入您要启动的 eureka 服务器的节点名称 —— [详细配置请查看application.yml 文件中配置的 spring.profiles 的值]");
        System.err.println("请输入 slave1 或者 slave2");
        Scanner scanner = new Scanner(System.in);
        String profiles = scanner.nextLine();//让用户输入端口号
        new SpringApplicationBuilder(EurekaServer.class).profiles(profiles).run(args);//启动项目

        System.out.println("============================================================= eureka服务启动成功 =============================================================");
//    	SpringApplication.run(EurekaServer.class, args);
    }
}
