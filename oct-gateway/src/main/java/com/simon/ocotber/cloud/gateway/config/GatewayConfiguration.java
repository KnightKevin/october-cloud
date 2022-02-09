package com.simon.ocotber.cloud.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simon.ocotber.cloud.gateway.filter.OctoberRequestGlobalFilter;
import com.simon.ocotber.cloud.gateway.filter.PasswordDecoderFilter;
import com.simon.ocotber.cloud.gateway.handler.GlobalExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 * */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {
    @Bean
    public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties properties) {
        return new PasswordDecoderFilter(properties);
    }

    @Bean
    public OctoberRequestGlobalFilter octoberRequestGlobalFilter() {
        return new OctoberRequestGlobalFilter();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalExceptionHandler(objectMapper);
    }
}
