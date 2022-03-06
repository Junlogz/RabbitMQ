package com.zjl.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @author: JunLog
 * @Description: *
 * Date: 2022/3/4 12:02
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址;默认为 localhost
        connectionFactory.setHost("124.222.211.143");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/admin");
        //连接用户名；默认为guest
        connectionFactory.setUsername("admin");
        //连接密码；默认为guest
        connectionFactory.setPassword("admin");
        //创建连接
        return connectionFactory.newConnection();
    }
}
