package com.alekseyz.testtask.tracktime.service.implementation;

import com.alekseyz.testtask.tracktime.service.TestAllMethodService;
import com.alekseyz.testtask.tracktime.service.TestMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestAllMethodServiceImplementation implements TestAllMethodService {

    private final TestMethodService testMethodService;

    public void runAllTestMethods(){
        for (int i =0;i<30;i++){
            testMethodService.asyncFibonacciSumTestMethod(100);
            testMethodService.syncFibonacciSumTestMethod(100);
            testMethodService.asyncFactorialTestMethod(100);
            testMethodService.syncFactorialTestMethod(100);
        }
    }
}
