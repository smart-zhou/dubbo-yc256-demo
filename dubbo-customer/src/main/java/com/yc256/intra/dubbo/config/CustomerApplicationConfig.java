package com.yc256.intra.dubbo.config;

/**
 * Created by admin on 2016/8/15.
 */
public class CustomerApplicationConfig {
    private String appName;

    private String zookeeperAddress;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getZookeeperAddress() {
        return zookeeperAddress;
    }

    public void setZookeeperAddress(String zookeeperAddress) {
        this.zookeeperAddress = zookeeperAddress;
    }
}
