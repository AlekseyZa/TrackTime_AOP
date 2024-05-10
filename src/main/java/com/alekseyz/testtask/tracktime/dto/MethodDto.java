package com.alekseyz.testtask.tracktime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Schema(description = "Сущность метода для отображения")
@Value
@Builder
public class MethodDto {
    @Schema(description = "Идентификатор")
    Long id;
    @Schema(description = "Имя метода")
    String methodName;
    @Schema(description = "Класс метода")
    String className;
    @Schema(description = "время старта")
    LocalDateTime startTime;
    @Schema(description = "время остановки")
    LocalDateTime endTime;
    @Schema(description = "длительность выполнения")
    Long executionTime;
}
