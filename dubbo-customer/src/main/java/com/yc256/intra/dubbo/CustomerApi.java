package com.yc256.intra.dubbo;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.yc256.intra.dubbo.api.CustomerApiHolder;

import java.lang.reflect.Field;

/**
 * 消费者
 * Created by admin on 2016/8/13.
 */
public class CustomerApi {

    public static void main(String[] args) {
//        CustomerApi.start();
//        System.out.println(CustomerApi.helloWorldService.sayHello("TEST"));
        test();
    }

    private static void test() {
        ApplicationConfig application = new ApplicationConfig("test-smart");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://198.74.98.238:2181");
        Field[] fields = CustomerApiHolder.class.getDeclaredFields();
        for (Field field : fields) {
            Reference an = field.getAnnotation(Reference.class);
            ReferenceConfig ref = new ReferenceConfig();
            ref.setInterface(field.getType().getName());
            ref.setApplication(application);
            ref.setRegistry(registryConfig);
            if (StringUtils.isNotEmpty(an.version())) {
                ref.setVersion(an.version());
            }
            Object service = ref.get();
            try {
                field.set(CustomerApiHolder.class, service);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String str = CustomerApiHolder.helloWorldService.sayHello("smart");
        System.out.println(str);
    }
}
