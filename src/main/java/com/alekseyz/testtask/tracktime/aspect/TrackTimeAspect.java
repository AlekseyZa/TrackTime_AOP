package com.alekseyz.testtask.tracktime.aspect;

import com.alekseyz.testtask.tracktime.entity.TrackTimeInfo;
import com.alekseyz.testtask.tracktime.service.AsyncSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {

    private final AsyncSaveService asyncSaveService;
    private final Executor trackTimeExecutor;

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isService() {
    }

    @Pointcut("@annotation(com.alekseyz.testtask.tracktime.annotation.TrackTime)")
    public void isNotAsyncMethod() {
    }

    @Pointcut("@annotation(com.alekseyz.testtask.tracktime.annotation.TrackAsyncTime)")
    public void isAsyncMethod() {
    }

    @Around("isService()&&isNotAsyncMethod()")
    public Object TrackTimeNotAsyncMethod(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            LocalDateTime startTime = LocalDateTime.now();
            Object proceed = proceedingJoinPoint.proceed();
            LocalDateTime endTime = LocalDateTime.now();
            TrackTimeInfo trackTimeInfo = TrackTimeInfo.builder()
                    .className(proceedingJoinPoint.getSignature().getDeclaringTypeName())
                    .methodName(proceedingJoinPoint.getSignature().getName())
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();
            asyncSaveService.addTrackTimeInfoForSave(trackTimeInfo);
            return proceed;
        } catch (Throwable e) {
            log.error("Произошла ошибка при вызове метода: {}, ошибка: {}, причина: {}",
                    proceedingJoinPoint.getSignature().getName(), e.getMessage(), e.getCause());
        }
        return proceedingJoinPoint;
    }

    @Around("isService()&&isAsyncMethod()")
    public Object TrackTimeAsyncMethod(ProceedingJoinPoint proceedingJoinPoint) {
        TrackTimeInfo trackTimeInfo = new TrackTimeInfo();
        System.out.println("В аспекте до completable " + Thread.currentThread().getName());
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Метод {} начал работу.",  proceedingJoinPoint.getSignature().getName());
                LocalDateTime startTime = LocalDateTime.now();
                proceedingJoinPoint.proceed();
                LocalDateTime endTime = LocalDateTime.now();
                log.info("Метод {} завершил работу работу.",  proceedingJoinPoint.getSignature().getName());
                trackTimeInfo.setClassName(proceedingJoinPoint.getSignature().getDeclaringTypeName());
                trackTimeInfo.setMethodName(proceedingJoinPoint.getSignature().getName());
                trackTimeInfo.setStartTime(startTime);
                trackTimeInfo.setEndTime(endTime);
            } catch (Throwable e) {
                log.error("Произошла ошибка при вызове метода: {}, ошибка: {}",
                        proceedingJoinPoint.getSignature().getName(), e.getMessage());
            }
        }, trackTimeExecutor).thenRun(() ->
            asyncSaveService.addTrackTimeInfoForSave(trackTimeInfo)
        );
        return proceedingJoinPoint;
    }
}
