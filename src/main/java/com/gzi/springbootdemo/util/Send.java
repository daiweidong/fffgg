package com.gzi.springbootdemo.util;

import com.gzi.springbootdemo.dataobject.User;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Service
public class Send {
    //队列名称
    private static final String QUEUE_NAME = "test_simple_queue";

    public static void send(User user)
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


            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, user.toString().getBytes("utf-8"));
            System.out.println("[send]：" + user.toString());
            channel.close();
            connection.close();
        }
        catch (IOException | TimeoutException e)
        {
            e.printStackTrace();
        }
    }
}
