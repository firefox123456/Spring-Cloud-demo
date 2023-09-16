package hunan.yishi.univercity.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 多环境maven切换测试
 *
 * @author "黄骐"
 * @date 2023/07/08 15:50
 **/
@RestController
@RequestMapping("register")
@RefreshScope
public class RegistryController {

    @Value("${huangqi}")
    String huangqi;

    @GetMapping("sayAihao")
    public String sayAihao(){
        return huangqi;
    }

}
