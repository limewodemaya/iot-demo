package com.example.iotdemo.param;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceReportUpdateParam {

    private String deviceId;
    private LocalDateTime reportTime;
}
