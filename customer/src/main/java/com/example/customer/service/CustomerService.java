package com.example.customer.service;

import com.example.amqp.RabbitMqMessageProducer;
import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.NotificationRequest;
import com.example.customer.CustomerRepository;
import com.example.customer.dto.CustomerRegistrationRequest;
import com.example.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMqMessageProducer rabbitMqMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudReponse = fraudClient.isFraudster(customer.getId());
        if (fraudReponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        NotificationRequest notificationRequest =
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        customer.getLastName(),
                        String.format("Hi %s , welcome to AmigosCode...", customer.getFirstName()),
                        LocalDateTime.now()
        );
        rabbitMqMessageProducer.sendMessage(notificationRequest,exchange,routingKey);
    }

}
