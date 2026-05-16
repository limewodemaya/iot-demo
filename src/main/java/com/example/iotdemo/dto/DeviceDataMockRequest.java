package com.example.iotdemo.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeviceDataMockRequest {

    @NotBlank(message = "deviceId cannot be blank")
    private String deviceId;

    private LocalDateTime reportTime;

    private BigDecimal temperature;

    private BigDecimal voltage;

    private BigDecimal currentValue;

    private BigDecimal power;

    private BigDecimal soc;
}
