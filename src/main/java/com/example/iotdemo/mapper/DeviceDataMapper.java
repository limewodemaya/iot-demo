package com.example.iotdemo.mapper;

import com.example.iotdemo.entity.DeviceData;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceDataMapper {
    void insert(DeviceData deviceData);

    List<DeviceData> findHistory(@Param("deviceId") String deviceId,
                                 @Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);

//    int insert(DeviceData deviceData);

//    List<DeviceData> selectByDeviceId(Long deviceId);
}
