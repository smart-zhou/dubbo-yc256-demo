package com.yc256.intra.dubbo.application;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.yc256.intra.dubbo.config.CustomerApplicationConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;

/**
 *
 * Created by admin on 2016/8/13.
 */
public class CustomerApplication extends BaseApplicationContext{

    public CustomerApplication(CustomerApplicationConfig config, String... configLocations) throws BeansException {
        super(config, configLocations);
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(getConfig().getZookeeperAddress());
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(getConfig().getAppName());
        return applicationConfig;
    }

}
