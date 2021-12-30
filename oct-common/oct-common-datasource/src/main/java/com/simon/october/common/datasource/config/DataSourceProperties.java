package com.simon.october.common.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("Spring.datasource")
public class DataSourceProperties {
    private String username;
    private String password;

    /**
     * jdbc url
     * */
    private String url;
    private String driverClassName;

    /**
     *  查询数据源的SQL
     * */
    private String queryDsSql="select * from gen_datasource_conf where del_flag = 0";
}
