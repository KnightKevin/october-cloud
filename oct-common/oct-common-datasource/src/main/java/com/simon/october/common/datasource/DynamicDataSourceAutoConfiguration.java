package com.simon.october.common.datasource;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.simon.october.common.datasource.config.DataSourceProperties;
import com.simon.october.common.datasource.config.JdbcDynamicDataSourceProvider;
import com.simon.october.common.datasource.config.LastParamDsProcessor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DynamicDataSourceAutoConfiguration {
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider(StringEncryptor stringEncryptor, DataSourceProperties properties) {
        return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
    }

    @Bean
    public DsProcessor dsProcessor() {
        return new LastParamDsProcessor();
    }
}
