package com.example.iotdemo.mapper;

import com.example.iotdemo.entity.Device;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceMapper {

    int insert(Device device);

    Device findByDeviceId(@Param("deviceId") String deviceId);

    Device findById(@Param("id") Long id);

    List<Device> findAll();

//    void updateReportInfo(@NotBlank(message = "deviceId不能为空") String deviceId, @NotNull(message = "reportTime不能为空") LocalDateTime reportTime);
    void updateReportInfo(@Param("deviceId") String deviceId,
                          @Param("reportTime") LocalDateTime reportTime);
}