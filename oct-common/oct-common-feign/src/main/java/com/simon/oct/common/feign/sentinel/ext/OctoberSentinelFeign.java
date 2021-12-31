package com.simon.oct.common.feign.sentinel.ext;

import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class OctoberSentinelFeign {
    private OctoberSentinelFeign() {
    }

    public static OctoberSentinelFeign.Builder builder() {
        return new OctoberSentinelFeign.Builder();
    }

    public static final class Builder extends Feign.Builder implements ApplicationContextAware {
        private Contract contract = new Contract.Default();

        private ApplicationContext applicationContext;

        private FeignContext feignContext;

        @Override
        public Feign.Builder invocationHandlerFactory(InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Feign.Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                @Override
                public InvocationHandler create(Target target, Map<Method, MethodHandler> map) {
                    FeignClient feignClient = AnnotationUtils.findAnnotation(target.type(), FeignClient.class);
                    Class<?> fallback = feignClient.fallback();
                    Class<?> fallbackFactory = feignClient.fallbackFactory();

                    String beanName = feignClient.contextId();
                    if (!StringUtils.hasText(beanName)) {
                        beanName = feignClient.name();
                    }

                    Object fallbackInstance;
                    FallbackFactory<?> fallbackFactoryInstance;
                    if (void.class != fallback) {
                        fallbackInstance = getFromContext(beanName, "fallback", fallback, target.type());

                    }
                }
            });
            return super.build();
        }

        private Object getFromContext(String name, String type, Class<?> fallbackType, Class<?> targetType) {
            Object fallbackInstance = feignContext.getInstance(name, fallbackType);
            if (fallbackInstance == null) {
                throw new IllegalStateException(String.format("No %s instance of %s found for feign client %s", type, fallbackType, name));
            }

            if (!targetType.isAssignableFrom(fallbackType)) {
                throw new IllegalStateException(String.format("Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s", type, fallbackType, targetType, name));
            }

            return fallbackInstance;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }
    }
}
