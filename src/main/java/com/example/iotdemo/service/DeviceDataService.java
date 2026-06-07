package com.example.iotdemo.service;

import com.example.iotdemo.dto.DeviceDataReportRequest;
import com.example.iotdemo.entity.Device;
import com.example.iotdemo.entity.DeviceData;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.mapper.DeviceDataMapper;
import com.example.iotdemo.mapper.DeviceMapper;
import com.example.iotdemo.param.DeviceDataHistoryQueryParam;
import com.example.iotdemo.param.DeviceReportUpdateParam;
import com.example.iotdemo.vo.DeviceDataVO;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
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

    @Transactional
    public DeviceDataVO report(DeviceDataReportRequest request) {
        Device device = deviceMapper.findByDeviceId(request.getDeviceId());
        if (device == null) {
            throw new BusinessException("device not found, deviceId=" + request.getDeviceId());
        }

        if (request.getReportTime().isAfter(LocalDateTime.now().plusMinutes(5))) {
            throw new BusinessException("reportTime cannot be more than 5 minutes ahead");
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
        deviceMapper.updateReportInfo(new DeviceReportUpdateParam(
                request.getDeviceId(),
                request.getReportTime()
        ));

        return toVO(deviceData);
    }

    public List<DeviceDataVO> listByDeviceId(Long deviceId) {
        return deviceDataList.stream()
                .filter(deviceData -> deviceData.getDeviceId().equals(deviceId))
                .toList();
    }

    public List<DeviceDataVO> history(
            @NotBlank(message = "deviceId must not be blank") String deviceId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        Device device = deviceMapper.findByDeviceId(deviceId);
        if (device == null) {
            throw new BusinessException("device not found, deviceId=" + deviceId);
        }

        if (startTime == null || endTime == null) {
            throw new BusinessException("startTime and endTime must not be null");
        }

        if (startTime.isAfter(endTime)) {
            throw new BusinessException("startTime cannot be after endTime");
        }

        List<DeviceData> list = deviceDataMapper.findHistory(new DeviceDataHistoryQueryParam(
                deviceId,
                startTime,
                endTime
        ));

        return list.stream()
                .map(this::toVO)
                .collect(Collectors.toList());
    }

    private DeviceDataVO toVO(DeviceData deviceData) {
        DeviceDataVO vo = new DeviceDataVO();
        vo.setId(deviceData.getId());
        vo.setDeviceId(deviceData.getDeviceId());
        vo.setReportTime(deviceData.getReportTime());
        vo.setTemperature(deviceData.getTemperature());
        vo.setVoltage(deviceData.getVoltage());
        vo.setCurrentValue(deviceData.getCurrentValue());
        vo.setPower(deviceData.getPower());
        vo.setSoc(deviceData.getSoc());
        vo.setCreatedAt(deviceData.getCreatedAt());
        return vo;
    }
}
