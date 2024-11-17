package com.rabbitmq.practise.rabbitmq_demo.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue}")
    private String queueName;
    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;
    @Value("${spring.rabbitmq.routing_key}")
    private String routing_key;

    @Value("${spring.rabbitmq.json.queue}")
    private String jsonQueueName;

    @Value("${spring.rabbitmq.json.routing_key}")
    private String json_routing_key;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public Queue jsonqueue(){
        return new Queue(jsonQueueName);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing_key);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonqueue())
                .to(exchange())
                .with(json_routing_key);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
