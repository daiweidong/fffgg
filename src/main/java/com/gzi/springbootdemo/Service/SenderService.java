package com.gzi.springbootdemo.Service;

import com.gzi.springbootdemo.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue(String message) {

        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
    }

    public void sendTopic(String message) {

        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "dwd.message", message );
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "dwd.dwd", message);
    }

    public void sendFanout(String message) {
        // 注意， 这里的第2个参数为空。
        // 因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
        // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
        this.amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message );
    }
}
