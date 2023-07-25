package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.util.Properties;

public class DruidUtils {
    static DruidDataSource druidDataSource;

    static {
        Properties properties = new Properties();
        InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }
}
