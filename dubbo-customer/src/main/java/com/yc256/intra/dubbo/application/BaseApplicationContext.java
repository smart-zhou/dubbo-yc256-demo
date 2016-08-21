package com.yc256.intra.dubbo.application;

import com.yc256.intra.dubbo.config.CustomerApplicationConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by admin on 2016/8/15.
 */
public class BaseApplicationContext<T extends CustomerApplicationConfig> extends ClassPathXmlApplicationContext {

    private T config;

    public BaseApplicationContext(T config, String... configLocations) throws BeansException {
        super(configLocations);
        this.config = config;
    }

    public T getConfig() {
        return config;
    }

    public void setConfig(T config) {
        this.config = config;
    }

}
