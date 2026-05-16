package com.example.iotdemo.controller;

import com.example.iotdemo.common.Result;
import com.example.iotdemo.dto.DeviceDataMockRequest;
import com.example.iotdemo.service.DeviceDataService;
import com.example.iotdemo.vo.DeviceDataVO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device-data")
@RequiredArgsConstructor
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    @PostMapping("/mock")
    public Result<DeviceDataVO> mock(@Valid @RequestBody DeviceDataMockRequest request) {
        return Result.success(deviceDataService.mock(request));
    }

    @GetMapping("/{deviceId}")
    public Result<List<DeviceDataVO>> listByDeviceId(@PathVariable Long deviceId) {
        return Result.success(deviceDataService.listByDeviceId(deviceId));
    }
}
