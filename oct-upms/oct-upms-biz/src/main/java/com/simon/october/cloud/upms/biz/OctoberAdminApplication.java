package com.simon.october.cloud.upms.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootConfiguration
public class OctoberAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(OctoberAdminApplication.class, args);
    }
}
