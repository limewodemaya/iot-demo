package com.example.iotdemo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceDataMockRequest {

    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    @NotNull(message = "reportTime不能为空")
    private LocalDateTime reportTime;

    @DecimalMin(value = "-50.0", message = "temperature不能低于-50")
    @DecimalMax(value = "150.0", message = "temperature不能高于150")
    private BigDecimal temperature;

    @DecimalMin(value = "0.0", message = "voltage不能小于0")
    private BigDecimal voltage;

    @DecimalMin(value = "0.0", message = "currentValue不能小于0")
    private BigDecimal currentValue;

    @DecimalMin(value = "0.0", message = "power不能小于0")
    private BigDecimal power;

    @DecimalMin(value = "0.0", message = "soc不能小于0")
    @DecimalMax(value = "100.0", message = "soc不能大于100")
    private BigDecimal soc;
}