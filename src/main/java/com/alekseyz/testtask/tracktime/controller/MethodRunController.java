package com.alekseyz.testtask.tracktime.controller;

import com.alekseyz.testtask.tracktime.service.TestAllMethodService;
import com.alekseyz.testtask.tracktime.service.TestMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name="Запуск методов."
        , description="Позволяет запустить методы для отслеживания времени их выполнения через АОП (для тестирования приложения).")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/methods/")
public class MethodRunController {

    private final TestMethodService testMethodService;
    private final TestAllMethodService testAllMethodService;

    @Operation(
            summary = "Расчет чисел Фибоначчи (синх.).",
            description = "Расчитывает сумму чисел Фибоначчи по переданному числу с синхронным замером времени выполнения."
    )
    @GetMapping("/sync-fibonacci-sum/{number}")
    public void runSyncFibonacciSum(@PathVariable("number") @Parameter(description = "число для расчета") int number) {
        testMethodService.syncFibonacciSumTestMethod(number);
    }

    @Operation(
            summary = "Расчет чисел Фибоначчи (асинх.).",
            description = "Расчитывает сумму чисел Фибоначчи по переданному числу с асинхронным замером времени выполнения."
    )
    @GetMapping("/async-fibonacci-sum/{number}")
    public void runAsyncFibonacciSum(@PathVariable("number") @Parameter(description = "число для расчета") int number) {
        testMethodService.asyncFibonacciSumTestMethod(number);
    }

    @Operation(
            summary = "Расчет Факториала (синх.).",
            description = "Расчитывает Факториал по переданному числу с синхронным замером времени выполнения."
    )
    @GetMapping("/sync-factorial/{number}")
    public void runSyncFactorial(@PathVariable("number") @Parameter(description = "число для расчета") int number) {
        testMethodService.syncFactorialTestMethod(number);
    }

    @Operation(
            summary = "Расчет Факториала (асинх.).",
            description = "Расчитывает Факториал по переданному числу с асинхронным замером времени выполнения."
    )
    @GetMapping("/async-factorial/{number}")
    public void runAsyncFactorial(@PathVariable("number") @Parameter(description = "число для расчета") int number) {
        testMethodService.asyncFactorialTestMethod(number);
    }

    @Operation(
            summary = "Многократный запуск всех методов.",
            description = "Запускает многократный запуск всех тестовых методов (Фибоначчи и факториал). " +
                    "Удобно для быстрого тестирования."
    )
    @GetMapping("/run-all")
    public void runAllMethods() {
        testAllMethodService.runAllTestMethods();
    }

}
