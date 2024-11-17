package com.rabbitmq.practise.rabbitmq_demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RbbitMQConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RbbitMQConsumer.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consume(String message){
        LOGGER.info(String.format("message recieved=> %s",message));
    }
}
