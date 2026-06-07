package com.example.iotdemo.controller;

import com.example.iotdemo.common.Result;
import com.example.iotdemo.dto.DeviceDataReportRequest;
import com.example.iotdemo.service.DeviceDataService;
import com.example.iotdemo.vo.DeviceDataVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device-data")
@RequiredArgsConstructor
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    @PostMapping({"/report", "/mock"})
    public Result<DeviceDataVO> report(@Valid @RequestBody DeviceDataReportRequest request) {
        return Result.success(deviceDataService.report(request));
    }

    @GetMapping("/{deviceId}")
    public Result<List<DeviceDataVO>> listByDeviceId(@PathVariable Long deviceId) {
        return Result.success(deviceDataService.listByDeviceId(deviceId));
    }

    @GetMapping("/history")
    public Result<List<DeviceDataVO>> history(
            @RequestParam @NotBlank(message = "deviceId must not be blank") String deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
    ) {
        return Result.success(deviceDataService.history(deviceId, startTime, endTime));
    }
}
