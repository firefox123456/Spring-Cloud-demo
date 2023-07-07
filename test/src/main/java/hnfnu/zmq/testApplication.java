package hnfnu.zmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class testApplication {
    public static void main(String[] args) {
        SpringApplication.run(testApplication.class,args);
    }

}
