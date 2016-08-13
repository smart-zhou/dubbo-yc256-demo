package com.yc256.intra.service.impl;

import com.yc256.intra.service.HelloWorldService;

/**
 * Created by admin on 2016/8/13.
 */
public class HelloWorldServiceImpl implements HelloWorldService{
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
