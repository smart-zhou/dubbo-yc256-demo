package com.yc256.intra.dubbo.util;

/**
 * 属性配置相关
 * Created by admin on 2016/9/14.
 */
public class PropertyHolder {
    private static String appName;

    private static String zookeeperAddress;

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        PropertyHolder.appName = appName;
    }

    public static String getZookeeperAddress() {
        return zookeeperAddress;
    }

    public static void setZookeeperAddress(String zookeeperAddress) {
        PropertyHolder.zookeeperAddress = zookeeperAddress;
    }
}
