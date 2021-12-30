package com.simon.october.common.datasource.config;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 参数数据源解析 @DS(#last）
 * */
public class LastParamDsProcessor extends DsProcessor {

    private static final String LAST_PREFIX = "#last";

    /**
     * 抽象匹配条件，匹配才会走当前执行器否则走下一个执行器、
     * @param key DS注解里面的内容
     * */
    @Override
    public boolean matches(String key) {
        if (key.startsWith(LAST_PREFIX)) {
            DynamicDataSourceContextHolder.clear();
            return true;
        }
        return false;
    }

    /**
     * 抽象最终决定数据源
     * @param methodInvocation 方法执行信息
     * @param key 数据源名称
     * */
    @Override
    public String doDetermineDatasource(MethodInvocation methodInvocation, String key) {
        Object[] arguments = methodInvocation.getArguments();
        return String.valueOf(arguments[arguments.length - 1]);
    }
}
