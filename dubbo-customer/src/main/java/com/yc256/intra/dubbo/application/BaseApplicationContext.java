package com.yc256.intra.dubbo.application;

import com.yc256.intra.dubbo.config.CustomerApplicationConfig;
import com.yc256.intra.dubbo.util.PropertyHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by admin on 2016/8/15.
 */
public class BaseApplicationContext<T extends CustomerApplicationConfig> extends ClassPathXmlApplicationContext {

    private T config;

    private ClassPathXmlApplicationContext applicationContext;

    public BaseApplicationContext(T config, String... configLocations) throws BeansException {
        super(configLocations);
        PropertyHolder.setAppName(config.getAppName());
        PropertyHolder.setZookeeperAddress(config.getZookeeperAddress());
        this.config = config;
        applicationContext = new ClassPathXmlApplicationContext("classpath*:config/dubbo-base.xml");
        applicationContext.start();
    }

    public T getConfig() {
        return config;
    }

    public void setConfig(T config) {
        this.config = config;
    }

}
