package com.example.notification.service;

import com.example.clients.notification.NotificationRequest;
import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.getToCustomerId())
                        .sendAt(notificationRequest.getSendAt())
                        .message(notificationRequest.getMessage())
                        .sender("AmigosCode")
                        .toCustomerEmail(notificationRequest.getToCustomerEmail())
                        .build()
        );
    }


}
