package com.alekseyz.testtask.tracktime.service;

import com.alekseyz.testtask.tracktime.annotation.TrackAsyncTime;
import com.alekseyz.testtask.tracktime.annotation.TrackTime;

public interface TestMethodService {

    void asyncFibonacciSumTestMethod(int n);

    @TrackTime
    void syncFibonacciSumTestMethod(int n);

    @TrackAsyncTime
    void asyncFactorialTestMethod(int n);

    @TrackTime
    void syncFactorialTestMethod(int n);


}
