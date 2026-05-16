package com.example.iotdemo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceCreateRequest {

    @NotBlank(message = "deviceId不能为空")
    private String deviceId;

    @NotBlank(message = "deviceName不能为空")
    private String deviceName;

    private String productKey;

    private String deviceType;
}