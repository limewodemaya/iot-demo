package com.example.iotdemo.mapper;

import com.example.iotdemo.entity.Device;
import com.example.iotdemo.param.DeviceReportUpdateParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceMapper {

    int insert(Device device);

    Device findByDeviceId(@Param("deviceId") String deviceId);

    Device findById(@Param("id") Long id);

    List<Device> findAll();

    void updateReportInfo(DeviceReportUpdateParam param);
}
