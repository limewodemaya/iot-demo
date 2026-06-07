package com.example.iotdemo.mapper;

import com.example.iotdemo.entity.DeviceData;
import com.example.iotdemo.param.DeviceDataHistoryQueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceDataMapper {

    void insert(DeviceData deviceData);

    List<DeviceData> findHistory(DeviceDataHistoryQueryParam param);
}
