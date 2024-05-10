package com.alekseyz.testtask.tracktime.mapper;

import com.alekseyz.testtask.tracktime.dto.MethodDto;
import com.alekseyz.testtask.tracktime.entity.TrackTimeInfo;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReadAllMethodsLogMapper implements Mapper<TrackTimeInfo, MethodDto> {
    @Override
    public MethodDto map(TrackTimeInfo trackTimeInfo) {
        return MethodDto.builder()
                .id(trackTimeInfo.getId())
                .className(trackTimeInfo.getClassName())
                .methodName(trackTimeInfo.getMethodName())
                .startTime(trackTimeInfo.getStartTime())
                .endTime(trackTimeInfo.getEndTime())
                .executionTime(Duration.between(trackTimeInfo.getStartTime(),trackTimeInfo.getEndTime()).toMillis())
                .build();
    }
}
