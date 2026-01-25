package com.example.requestscheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage {
    private Long userId;
    private String username;
    private long scheduledTime;
}