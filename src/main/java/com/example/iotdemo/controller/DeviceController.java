package com.example.iotdemo.controller;

import com.example.iotdemo.common.Result;
import com.example.iotdemo.dto.DeviceCreateRequest;
import com.example.iotdemo.service.DeviceService;
import com.example.iotdemo.vo.DeviceVO;
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
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public Result<DeviceVO> create(@Valid @RequestBody DeviceCreateRequest request) {
        return Result.success(deviceService.create(request));
    }

    @GetMapping
    public Result<List<DeviceVO>> list() {
        return Result.success(deviceService.list());
    }

    @GetMapping("/{id}")
    public Result<DeviceVO> getById(@PathVariable Long id) {
        return Result.success(deviceService.getById(id));
    }
}
