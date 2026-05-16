package com.example.iotdemo.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeviceVO {

    private Long id;
    private String deviceName;
    private String deviceCode;
    private String deviceType;
    private String location;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
