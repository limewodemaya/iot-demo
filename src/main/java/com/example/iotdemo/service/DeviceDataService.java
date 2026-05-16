package com.example.iotdemo.service;

import com.example.iotdemo.dto.DeviceDataMockRequest;
import com.example.iotdemo.vo.DeviceDataVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataService {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<DeviceDataVO> deviceDataList = new ArrayList<>();

    public DeviceDataVO mock(DeviceDataMockRequest request) {
        DeviceDataVO deviceData = new DeviceDataVO();
        deviceData.setId(idGenerator.getAndIncrement());
        deviceData.setDeviceId(request.getDeviceId());
        deviceData.setTemperature(request.getTemperature());
        // todo

        deviceDataList.add(deviceData);
        return deviceData;
    }

    public List<DeviceDataVO> listByDeviceId(Long deviceId) {
        return deviceDataList.stream()
                .filter(deviceData -> deviceData.getDeviceId().equals(deviceId))
                .toList();
    }
}
