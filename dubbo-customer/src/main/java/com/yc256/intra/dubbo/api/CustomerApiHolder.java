package com.yc256.intra.dubbo.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc256.intra.service.HelloWorldService;

/**
 * 消费者接口定义
 * Created by admin on 2016/10/12.
 */
public class CustomerApiHolder {

    @Reference
    public static HelloWorldService helloWorldService;
}
