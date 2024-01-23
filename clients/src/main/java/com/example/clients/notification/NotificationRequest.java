package com.example.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationRequest implements Serializable {
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sendAt;

    public NotificationRequest(Integer id, String email, String format) {
    }
}
