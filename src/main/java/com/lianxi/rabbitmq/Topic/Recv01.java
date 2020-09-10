package com.lianxi.rabbitmq.Topic;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv01 {

    private final static String QUEUE_NAME = "TOPIC_RABBITMQ_TEST";
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //根据连接获取通道
        Channel channel = connection.createChannel();

        //创建消息队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //将消息队列绑定到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.*");

        //消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String s = new String(body);

                System.out.println("[消费者1]："+s+"!");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
