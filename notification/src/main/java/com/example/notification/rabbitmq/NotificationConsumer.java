package com.example.notification.rabbitmq;

import com.example.clients.notification.NotificationRequest;
import com.example.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    @Value("${spring.rabbitmq.queue.name}")
    private String queue;
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final NotificationService notificationService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue ");
        notificationService.send(notificationRequest);
    }
}
