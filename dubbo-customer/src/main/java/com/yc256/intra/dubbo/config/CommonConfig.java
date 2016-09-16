package com.yc256.intra.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.yc256.intra.dubbo.util.PropertyHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by admin on 2016/9/14.
 */
@Configuration
public class CommonConfig {

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(PropertyHolder.getZookeeperAddress());
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(PropertyHolder.getAppName());
        return applicationConfig;
    }
}
