package com.example.customer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication(scanBasePackages = {"com.example.amqp", "com.example.customer"})
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = {
                "com.example.clients",
                "com.example.customer"
        }
)
@EnableRabbit
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
