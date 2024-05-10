package com.alekseyz.testtask.tracktime.controller;

import com.alekseyz.testtask.tracktime.dto.IAgregateExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.IGroupByAnnotationExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.MethodDto;
import com.alekseyz.testtask.tracktime.service.TrackTimeStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Статистика."
        , description="Позволяет посмотреть статистику выполнения методов.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistic/")
public class TrackTimeStatisticController {

    private final TrackTimeStatisticService trackTimeStatisticService;

    @Operation(
            summary = "Журнал выполнения методов.",
            description = "Выводит журнал с данными по запуску каждого метода (Лог запуска)."
    )
    @GetMapping("/method-execution-log")
    public List<MethodDto> getAllMethodLog() {
        return trackTimeStatisticService.findAll();
    }

    @Operation(
            summary = "Агрегированная данные по методам.",
            description = "Выводит статистику с группировкой по методам."
    )
    @GetMapping("/agregate-method-execution")
    public List<IAgregateExecutionTimeMethod> agregateMethodExecution() {
        return trackTimeStatisticService.findAgregateExecutionMethod();
    }

    @Operation(
            summary = "Агрегированные данные по аннотациям.",
            description = "Выводит статистику с группировкой по аннотациям (TrackTime/TrackAsyncTime)."
    )
    @GetMapping("/groupbyannotation-method-execution")
    public List<IGroupByAnnotationExecutionTimeMethod> GroupByAnnotationMethodExecution() {
        return trackTimeStatisticService.findGroupByAnnotationExecutionMethod();
    }

}
