package per.luo.server.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * API网关
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
//        //SpringApplication.run(ZuulApplication.class, args);
        System.out.println("================================================== 开始启动 zuul网关服务 =============================================================");
//        System.out.println("请在控制台指定zuul网关服务的端口号 —— [端口号随意指定，注意不要与本机端口号出现冲突即可]");
//
//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine(); //让用户指定端口号
//        new SpringApplicationBuilder(ZuulApplication.class).properties("server.port=" + port).run(args);//启动项目

        SpringApplication.run(ZuulApplication.class, args);

        System.out.println("================================================== zuul网关服务 启动成功 =============================================================");
    }
}
