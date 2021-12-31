package com.simon.oct.common.feign;

import feign.Feign;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OctoberFeignAutoConfiguration {
    public Feign.Builder feignSentinelBuilder() {
        return
    }
}
