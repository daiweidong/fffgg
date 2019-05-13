package com.gzi.springbootdemo.config;


import com.gzi.springbootdemo.dataobject.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(String message) {
        System.err.println("【receiverDirectQueue监听到消息】" + message);
    }

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        System.err.println("【receiveTopic1监听到消息】" + message);
    }
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        System.err.println("【receiveTopic2监听到消息】" + message);
    }

}
