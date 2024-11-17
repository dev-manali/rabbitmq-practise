package com.rabbitmq.practise.rabbitmq_demo.producer;

import com.rabbitmq.practise.rabbitmq_demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.json.routing_key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void jsonMessage(User user){
        LOGGER.info(String.format("Sent json message => %s ",user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}
