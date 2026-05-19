package com.example.iotdemo.service;

import com.example.iotdemo.dto.DeviceDataMockRequest;
import com.example.iotdemo.entity.Device;
import com.example.iotdemo.entity.DeviceData;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.mapper.DeviceDataMapper;
import com.example.iotdemo.mapper.DeviceMapper;
import com.example.iotdemo.vo.DeviceDataVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.example.iotdemo.vo.DeviceVO;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceDataService {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<DeviceDataVO> deviceDataList = new ArrayList<>();

    @Resource
    private DeviceDataMapper deviceDataMapper;
    @Resource
    private DeviceMapper deviceMapper;

//    public DeviceDataVO mock(DeviceDataMockRequest request) {
//        DeviceDataVO deviceData = new DeviceDataVO();
//        deviceData.setId(idGenerator.getAndIncrement());
//        deviceData.setDeviceId(request.getDeviceId());
//        deviceData.setTemperature(request.getTemperature());
//
//
//        deviceDataList.add(deviceData);
//        return deviceData;
//    }

    @Transactional
    public DeviceDataVO mockReport(DeviceDataMockRequest request) {
        Device device = deviceMapper.findByDeviceId(request.getDeviceId());
        if (device == null) {
            throw new BusinessException("设备不存在，deviceId=" + request.getDeviceId());
        }

        if (request.getReportTime().isAfter(LocalDateTime.now().plusMinutes(5))) {
            throw new BusinessException("上报时间不能超过当前时间5分钟");
        }

        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(request.getDeviceId());
        deviceData.setReportTime(request.getReportTime());
        deviceData.setTemperature(request.getTemperature());
        deviceData.setVoltage(request.getVoltage());
        deviceData.setCurrentValue(request.getCurrentValue());
        deviceData.setPower(request.getPower());
        deviceData.setSoc(request.getSoc());

        deviceDataMapper.insert(deviceData);

        deviceMapper.updateReportInfo(
                request.getDeviceId(),
                request.getReportTime()
        );

        return toVO(deviceData);
    }

    public List<DeviceDataVO> listByDeviceId(Long deviceId) {
        return deviceDataList.stream()
                .filter(deviceData -> deviceData.getDeviceId().equals(deviceId))
                .toList();
    }

    public List<DeviceDataVO> history(@NotBlank(message = "deviceId不能为空") String deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        Device device = deviceMapper.findByDeviceId(deviceId);
        if (device == null) {
            throw new BusinessException("设备不存在，deviceId=" + deviceId);
        }

        if (startTime == null || endTime == null) {
            throw new BusinessException("开始时间和结束时间不能为空");
        }

        if (startTime.isAfter(endTime)) {
            throw new BusinessException("开始时间不能晚于结束时间");
        }

        List<DeviceData> list = deviceDataMapper.findHistory(deviceId, startTime, endTime);

        return list.stream()
                .map(this::toVO)
                .collect(Collectors.toList());
    }

    private DeviceDataVO toVO(DeviceData deviceData) {
        DeviceDataVO vo = new DeviceDataVO();
        vo.setId(deviceData.getId());
        vo.setDeviceId(deviceData.getDeviceId());
        vo.setPower(deviceData.getPower());
        vo.setSoc(deviceData.getSoc());
        vo.setCurrentValue(deviceData.getCurrentValue());
        vo.setCreatedAt(deviceData.getCreatedAt());
        vo.setVoltage(deviceData.getVoltage());
        return vo;
    }
}
