package com.yc256.intra.dubbo;

import com.yc256.intra.dubbo.application.CustomerApplication;
import com.yc256.intra.dubbo.config.CustomerApplicationConfig;
import com.yc256.intra.service.HelloWorldService;

/**
 * 消费者
 * Created by admin on 2016/8/13.
 */
public class CustomerApi {

    private static final CustomerApplication customerApplication = new CustomerApplication(
            new CustomerApplicationConfig("dubbo-customer", "zookeeper://198.74.98.238:2181"),
            "classpath*:config/dubbo-customer.xml");

    private static HelloWorldService helloWorldService;

    private static void init() {
        helloWorldService = customerApplication.getBean(HelloWorldService.class);
    }

    public CustomerApi() {
    }

    private static void checkApplication() {
        if (null == customerApplication) {
            throw new RuntimeException("未初始化成功");
        }
    }

    public static void start() {
        checkApplication();
        customerApplication.start();
        init();
    }

    public static void main(String[] args) {
        CustomerApi.start();
        System.out.println(CustomerApi.helloWorldService.sayHello("TEST"));
    }
}
