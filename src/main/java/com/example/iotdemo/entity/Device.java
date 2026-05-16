package com.example.iotdemo.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Device {
    private Long id;
    private String deviceId;
    private String deviceName;
    private String productKey;
    private String deviceType;
    private String status;
    private LocalDateTime lastReportTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
