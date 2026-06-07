package com.example.iotdemo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceDataReportRequest {

    @NotBlank(message = "deviceId must not be blank")
    private String deviceId;

    @NotNull(message = "reportTime must not be null")
    private LocalDateTime reportTime;

    @DecimalMin(value = "-50.0", message = "temperature must be >= -50")
    @DecimalMax(value = "150.0", message = "temperature must be <= 150")
    private BigDecimal temperature;

    @DecimalMin(value = "0.0", message = "voltage must be >= 0")
    private BigDecimal voltage;

    @DecimalMin(value = "0.0", message = "currentValue must be >= 0")
    private BigDecimal currentValue;

    @DecimalMin(value = "0.0", message = "power must be >= 0")
    private BigDecimal power;

    @DecimalMin(value = "0.0", message = "soc must be >= 0")
    @DecimalMax(value = "100.0", message = "soc must be <= 100")
    private BigDecimal soc;
}
