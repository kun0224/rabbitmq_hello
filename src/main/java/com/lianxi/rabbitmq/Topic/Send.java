package com.lianxi.rabbitmq.Topic;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //根据连接获取通道
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        //消息内容
        String message = "hello TOPIC";

        //发送消息到交换机
        channel.basicPublish(EXCHANGE_NAME, "item.hello", null, message.getBytes());

        System.out.println("[生产者]："+message+"!");

        channel.close();
        connection.close();

    }

}
