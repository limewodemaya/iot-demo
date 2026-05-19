package com.example.iotdemo.controller;

import com.example.iotdemo.common.Result;
import com.example.iotdemo.dto.DeviceDataMockRequest;
import com.example.iotdemo.entity.DeviceData;
import com.example.iotdemo.service.DeviceDataService;
import com.example.iotdemo.vo.DeviceDataVO;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device-data")
@RequiredArgsConstructor
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    @PostMapping("/mock")
    public Result<DeviceDataVO> mock(@Valid @RequestBody DeviceDataMockRequest request) {
        return Result.success(deviceDataService.mockReport(request));
    }

    @GetMapping("/{deviceId}")
    public Result<List<DeviceDataVO>> listByDeviceId(@PathVariable Long deviceId) {
        return Result.success(deviceDataService.listByDeviceId(deviceId));
    }

    @GetMapping("/history")
    public Result<List<DeviceDataVO>> history(
            @RequestParam @NotBlank(message = "deviceId不能为空") String deviceId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endTime
    ) {
        return Result.success(deviceDataService.history(deviceId, startTime, endTime));
    }
}
