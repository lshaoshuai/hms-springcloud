package per.luo.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luoshao
 * @date 2019/5/14 11:46
 * @projectname HMS
 */

//拉取配置时更新Git仓库副本，保证是配置为最新;
//支持从yml、json、properties等文件加载配置;
//配合Eureke可实现服务发现，配合Cloud Bus(这个后面我们在详细说明)可实现配置推送更新;
//默认配置存储基于Git仓库(可以切换为SVN)，从而支持配置的版本管理.

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigApplication {

    public static void main(String[] args){

        System.out.println("================================================== 开始启动 Config Server配置中心服务 =============================================================");

        SpringApplication.run(ConfigApplication.class,args);

        System.out.println("================================================== Config Server配置中心服务启动成功 =============================================================");
    }
}
