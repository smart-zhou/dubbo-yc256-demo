package com.yc256.intra.dubbo.application;

import org.springframework.beans.BeansException;

/**
 * Created by admin on 2016/8/13.
 */
public class CustomerApplication<CustomerApplicationConfig> extends BaseApplicationContext{

    public CustomerApplication(CustomerApplicationConfig config, String... configLocations) throws BeansException {
        super(config, configLocations);
    }

}
