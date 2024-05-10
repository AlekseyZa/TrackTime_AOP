package com.alekseyz.testtask.tracktime.service.implementation;

import com.alekseyz.testtask.tracktime.annotation.TrackAsyncTime;
import com.alekseyz.testtask.tracktime.annotation.TrackTime;
import com.alekseyz.testtask.tracktime.service.TestMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
public class TestMethodServiceImplementation implements TestMethodService {

    @TrackAsyncTime
    public void asyncFibonacciSumTestMethod(int n) {
        Stream.iterate(new long[]{0, 1}, array -> new long[]{array[1], array[0] + array[1]})
                .limit(n)
                .map(x -> x[0])
                .mapToLong(Long::longValue)
                .sum();
        waitTime(1);
    }

    @TrackTime
    public void syncFibonacciSumTestMethod(int n) {
        Stream.iterate(new long[]{0, 1}, array -> new long[]{array[1], array[0] + array[1]})
                .limit(n)
                .map(x -> x[0])
                .mapToLong(Long::longValue)
                .sum();
        waitTime(1);
    }

    @TrackAsyncTime
    public void asyncFactorialTestMethod(int n) {
               if (n < 2) {
           BigInteger.valueOf(1);
        } else {
           IntStream.rangeClosed(2, n).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get();
        }
        waitTime(1);
    }

    @TrackTime
    public void syncFactorialTestMethod(int n) {
        if (n < 2) {
            BigInteger.valueOf(1);
        } else {
            IntStream.rangeClosed(2, n).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get();
        }
        waitTime(1);
    }

    public static void waitTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException("Ошибка потока" + e.getCause());
        }
    }

}
