package com.yc256.intra.dubbo;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1
 * Created by admin on 2016/8/13.
 */
public class Provider {

    private static Provider instance = new Provider();

    private static ClassPathXmlApplicationContext context = null;

    public Provider() {
    }

    public static Provider getInstance() {
        return instance;
    }

    public void start(String configLocation) {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:" + configLocation);
            context.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String configLocation = null;
        if (args != null && args.length > 0) {
            configLocation = args[0];
        }
        if (StringUtils.isBlank(configLocation)) {
            configLocation = "config/dubbo-provider.xml";
        }
        Provider.getInstance().start(configLocation);
        System.out.println("[PROVIDER SERVICE STARTED]");
        while (true) {
            Thread.sleep(1000);
        }
    }
}
