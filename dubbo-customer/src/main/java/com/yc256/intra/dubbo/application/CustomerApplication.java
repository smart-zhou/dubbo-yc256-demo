package com.yc256.intra.dubbo.application;

import com.yc256.intra.service.HelloWorldService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by admin on 2016/8/13.
 */
public class CustomerApplication {
    private ApplicationContext context;

    public CustomerApplication() {
        context = new ClassPathXmlApplicationContext("classpath:config/dubbo-customer.xml");
    }

    public CustomerApplication(ApplicationContext context) {
        this.context = context;
    }

    public <T> T getBean(Class<T> clazz) {
        if (null == context) {
            context = new ClassPathXmlApplicationContext("classpath:config/dubbo-customer.xml");
        }
        return context.getBean(clazz);
    }
}
