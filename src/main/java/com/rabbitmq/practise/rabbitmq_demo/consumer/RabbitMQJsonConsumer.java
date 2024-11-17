package com.rabbitmq.practise.rabbitmq_demo.consumer;

import com.rabbitmq.practise.rabbitmq_demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private  static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.json.queue}"})
    public void consume(User user) {
        LOGGER.info(String.format("Received json message => %s",user
        .toString()));
    }
}
