package hunan.yishi.univercity.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * nacos充当注册中心和配置中心demo
 *
 * @author "黄骐"
 * @date 2023/07/08 14:37
 **/
//复合注解包括自动配置的开启
@SpringBootApplication
//注册到nacos中去（名字为application.yml中配置的spring.cloud.name）
@EnableDiscoveryClient
public class RegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class,args);
    }
}
