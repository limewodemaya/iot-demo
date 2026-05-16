package com.example.iotdemo.service;

import com.example.iotdemo.dto.DeviceCreateRequest;
import com.example.iotdemo.entity.Device;
import com.example.iotdemo.exception.BusinessException;
import com.example.iotdemo.mapper.DeviceMapper;
import com.example.iotdemo.vo.DeviceVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceMapper deviceMapper;

    public DeviceVO create(DeviceCreateRequest request) {
        Device exists = deviceMapper.findByDeviceId(request.getDeviceId());
        if (exists != null) {
            throw new BusinessException("device already exists");
        }

        Device device = new Device();
        device.setDeviceId(request.getDeviceId());
        device.setDeviceName(request.getDeviceName());
        device.setProductKey(request.getProductKey());
        device.setDeviceType(request.getDeviceType());
        device.setStatus("OFFLINE");

        deviceMapper.insert(device);
        return toVO(device);
    }

    public List<DeviceVO> list() {
        return deviceMapper.findAll().stream()
                .map(this::toVO)
                .toList();
    }

    public DeviceVO getById(Long id) {
        Device device = deviceMapper.findById(id);
        if (device == null) {
            throw new BusinessException("device not found");
        }
        return toVO(device);
    }

    private DeviceVO toVO(Device device) {
        DeviceVO vo = new DeviceVO();
        vo.setId(device.getId());
        vo.setDeviceName(device.getDeviceName());
        vo.setDeviceCode(device.getProductKey());
        vo.setDeviceType(device.getDeviceType());
        vo.setLocation(null);
        vo.setStatus(device.getStatus());
        vo.setCreatedAt(device.getCreatedAt());
        vo.setUpdatedAt(device.getUpdatedAt());
        return vo;
    }

}
