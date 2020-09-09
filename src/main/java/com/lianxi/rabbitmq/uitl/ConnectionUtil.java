package com.lianxi.rabbitmq.uitl;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("zhao");
        factory.setPassword("123");
        factory.setHost("192.168.111.128");
        factory.setPort(5672);
        factory.setVirtualHost("/shopping");
        return factory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection con = ConnectionUtil.getConnection();
        System.out.println(con);
        con.close();

    }

}
