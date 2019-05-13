package com.gzi.springbootdemo.util;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

public class Receive {
    //队列名称
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void Receive()
    {
        try
        {
            //获取连接
            Connection connection = ConnectionUtil.getConnection();
            //从连接中获取一个通道
            Channel channel = connection.createChannel();
            //声明队列
            //在声明队列名称时，持久化队列，生产端和消费端都要
            //channel.queue_declare(queue='hello', durable=True)
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //定义消费者
            DefaultConsumer consumer = new DefaultConsumer(channel)
            {
               // channel.basic_publish(exchange='',routing_key='hello',body='hello',properties=pika.BasicProperties(delivery_mode=2,  # make message persistent))
              // 增加properties，这个properties 就是消费端 callback函数中的properties# delivery_mode = 2  持久化消息
                //当消息到达时执行回调方法
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException
                {
                    String message = new String(body, "utf-8");
                    System.out.println("[Receive]：" + message);
                }
            };
            //监听队列
            channel.basicConsume(QUEUE_NAME, true, consumer);
        }
        catch (IOException | ShutdownSignalException | ConsumerCancelledException e)
        {
            e.printStackTrace();
        }
    }
}
