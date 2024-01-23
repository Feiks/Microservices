package com.example.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqMessageProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Object payload, String exchange, String routingKey){
        log.info("Publishing to {} using routingKey {} . Payload: {} ", exchange, routingKey,payload);
        rabbitTemplate.convertAndSend(exchange,routingKey,payload);
        log.info("Published to {} using routingKey {} . Payload: {} ", exchange, routingKey,payload);

    }


}
