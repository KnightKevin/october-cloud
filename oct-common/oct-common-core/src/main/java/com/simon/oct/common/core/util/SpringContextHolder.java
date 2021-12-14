package com.simon.oct.common.core.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     *  取得存储在静态变量中的ApplicationContext
     * */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     *  实现ApplicationContextAware接口，注入Context到静态变量中
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     *  从静态变量applicationContext中取得Bean，并自动转型
     * */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为null
     * */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("clear SpringContextHolder.ApplicationContext,and set it null!");
        }
        applicationContext = null;
    }

    /**
     * 发布事件
     * @param event
     * */
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }

        applicationContext.publishEvent(event);
    }

    @Override
    @SneakyThrows
    public void destroy() {
        SpringContextHolder.clearHolder();
    }

}
