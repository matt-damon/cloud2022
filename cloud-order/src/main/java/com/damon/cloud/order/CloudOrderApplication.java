package com.damon.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.damon.cloud.api") //开启OpenFeign，扫描接口
public class CloudOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudOrderApplication.class, args);
    }

    @Bean
    @LoadBalanced  //与LoadBalancer集成
    public RestTemplate template() {
        return new RestTemplate();
    }

}