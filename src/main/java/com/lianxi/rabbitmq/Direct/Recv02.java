package com.lianxi.rabbitmq.Direct;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv02 {

    private final static String EXCHANGE_NAME = "fanout_exchange_test02";
    private final static String QUEUE_NAME="Direct_exchange_test02";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //根据连接创建通道
        Channel channel = connection.createChannel();

        //创建消息队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定消息队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "direct02");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert01");

        //定义队列的消费者
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

