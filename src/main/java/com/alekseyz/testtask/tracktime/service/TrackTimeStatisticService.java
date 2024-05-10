package com.alekseyz.testtask.tracktime.service;

import com.alekseyz.testtask.tracktime.dto.IAgregateExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.IGroupByAnnotationExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.MethodDto;

import java.util.List;

public interface TrackTimeStatisticService {


    List<MethodDto> findAll();

    List<IAgregateExecutionTimeMethod> findAgregateExecutionMethod();

    List<IGroupByAnnotationExecutionTimeMethod> findGroupByAnnotationExecutionMethod();
}
