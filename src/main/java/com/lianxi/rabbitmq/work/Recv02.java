package com.lianxi.rabbitmq.work;

import com.lianxi.rabbitmq.uitl.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv02 {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //获取通道
        Channel channel = connection.createChannel();

        //获取消息队列
        channel.queueDeclare(QUEUE_NAME, false,false,false, null);

        channel.basicQos(1);

        //处理消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String s = new String(body);

                System.out.println("[消费者2]received"+s+"!");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        };

        //监听
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
