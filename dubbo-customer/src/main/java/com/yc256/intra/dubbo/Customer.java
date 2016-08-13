package com.yc256.intra.dubbo;

import com.alibaba.dubbo.container.page.pages.SystemPageHandler;
import com.yc256.intra.dubbo.application.CustomerApplication;
import com.yc256.intra.service.HelloWorldService;

/**
 * 消费过者
 * Created by admin on 2016/8/13.
 */
public class Customer {

    private final CustomerApplication customerApplication = new CustomerApplication();

    private HelloWorldService helloWorldService;

    private void init() {
        helloWorldService = customerApplication.getBean(HelloWorldService.class);
    }

    public Customer() {
        init();
    }

    public String sayHello() {
        return helloWorldService.sayHello("China");
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        System.out.println(customer.sayHello());
    }
}
