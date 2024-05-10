package com.alekseyz.testtask.tracktime.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сгруппированная по аннотациям статистика")
public interface IGroupByAnnotationExecutionTimeMethod {
    @Schema(description = "Имя метода")
    String getMethodAnnotation();
    @Schema(description = "Среднее время выполнения")
    Long getAvgExecutionTime();
    @Schema(description = "Сумма времени выполнения")
    Long getSumExecutionTime();
    @Schema(description = "Минимально время выполнения")
    Long getMinExecutionTime();
    @Schema(description = "Максимальное время выполнения")
    Long getMaxExecutionTime();
    @Schema(description = "Количество запусков")
    Long getRunCount();
}
