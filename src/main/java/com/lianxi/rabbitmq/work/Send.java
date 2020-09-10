package com.lianxi.rabbitmq.work;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取到连接
        Connection connection = ConnectionUtil.getConnection();

        //获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //循环发布任务
        for (int i = 0; i < 50; i++) {

            String message = "task" + i;

            //像队列发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println("[x]sent"+message+"!");
        }


    }

}
