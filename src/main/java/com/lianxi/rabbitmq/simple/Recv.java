package com.lianxi.rabbitmq.simple;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.*;
import org.hamcrest.Condition;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Recv {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取到连接
        Connection connection = ConnectionUtil.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列中的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);

                String s = new String(body);

                System.out.println("[x]received:"+s+"!");
            }

        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
