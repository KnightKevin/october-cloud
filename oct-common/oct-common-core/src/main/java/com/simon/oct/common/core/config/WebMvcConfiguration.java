package com.simon.oct.common.core.config;

import cn.hutool.core.date.DatePattern;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 增加GET请求参数中时间类型转换
     * HH:mm:ss -> LocalTime
     * yyyy-MM-dd -> LocalDate
     * yyyy-MM-dd HH:mm:ss -> LocalDateTime
     * */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();

        registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
        registrar.registerFormatters(registry);

    }
}
