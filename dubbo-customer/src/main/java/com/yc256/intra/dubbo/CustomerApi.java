package com.yc256.intra.dubbo;

import com.yc256.intra.dubbo.application.CustomerApplication;
import com.yc256.intra.dubbo.config.CustomerApplicationConfig;
import com.yc256.intra.service.HelloWorldService;

/**
 * 消费过者
 * Created by admin on 2016/8/13.
 */
public class CustomerApi {

    private static final CustomerApplication customerApplication = new CustomerApplication(new CustomerApplicationConfig());

    private static HelloWorldService helloWorldService;

    static  {
        helloWorldService = customerApplication.getBean(HelloWorldService.class);
    }

    public CustomerApi() {
    }

    public static void main(String[] args) {
        System.out.println(CustomerApi.helloWorldService.sayHello("TEST"));
    }
}
