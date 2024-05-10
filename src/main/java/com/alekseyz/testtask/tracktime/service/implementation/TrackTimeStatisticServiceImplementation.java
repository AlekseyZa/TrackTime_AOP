package com.alekseyz.testtask.tracktime.service.implementation;

import com.alekseyz.testtask.tracktime.dto.IAgregateExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.IGroupByAnnotationExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.MethodDto;
import com.alekseyz.testtask.tracktime.mapper.ReadAllMethodsLogMapper;
import com.alekseyz.testtask.tracktime.repository.TrackTimeInfoRepository;
import com.alekseyz.testtask.tracktime.service.TrackTimeStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrackTimeStatisticServiceImplementation implements TrackTimeStatisticService {

    private final TrackTimeInfoRepository trackTimeInfoRepository;
    private final ReadAllMethodsLogMapper readAllMethodsLogMapper;

    @Override
    public List<MethodDto> findAll() {
        return trackTimeInfoRepository.findAll()
                .stream()
                .map(readAllMethodsLogMapper::map)
                .toList();
    }

    public List<IAgregateExecutionTimeMethod> findAgregateExecutionMethod() {
        return trackTimeInfoRepository.findAgregateExecutionMethod()
                .stream()
                .toList();

    }

    public List<IGroupByAnnotationExecutionTimeMethod> findGroupByAnnotationExecutionMethod() {
        return trackTimeInfoRepository.findGroupByAnnotationExecutionMethod()
                .stream()
                .toList();
    }


}
