package com.alekseyz.testtask.tracktime.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сгруппированная по методам статистика")
public interface IAgregateExecutionTimeMethod {
    @Schema(description = "Имя метода")
    String getMethodName();
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

