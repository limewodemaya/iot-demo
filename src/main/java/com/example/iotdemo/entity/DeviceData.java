package com.example.iotdemo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeviceData {

    private Long id;
    private String deviceId;
    private LocalDateTime reportTime;
    private BigDecimal temperature;
    private BigDecimal voltage;
    private BigDecimal currentValue;
    private BigDecimal power;
    private BigDecimal soc;
    private LocalDateTime createdAt;
}
