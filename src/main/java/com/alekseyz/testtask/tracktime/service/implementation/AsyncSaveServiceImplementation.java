package com.alekseyz.testtask.tracktime.service.implementation;

import com.alekseyz.testtask.tracktime.entity.TrackTimeInfo;
import com.alekseyz.testtask.tracktime.repository.TrackTimeInfoRepository;
import com.alekseyz.testtask.tracktime.service.AsyncSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AsyncSaveServiceImplementation implements AsyncSaveService {

    private final int MAX_CAPACITY = 100;

    private final TrackTimeInfoRepository trackTimeInfoRepository;
    private final BlockingQueue<TrackTimeInfo> blockingQueue = new ArrayBlockingQueue<>(MAX_CAPACITY);

    @Async("saveDBTaskExecutor")
    @Scheduled(fixedRate = 30)
    public void saveTrackTimeInfoTask() {
        try {
            trackTimeInfoRepository.save(blockingQueue.take());
        } catch (InterruptedException e) {
            log.error("Ошибка Schedule при сохранении данных: {}, причина: {}",
                    e.getMessage(), e.getCause());
        }
    }

    public void addTrackTimeInfoForSave(TrackTimeInfo trackTimeInfo) {
        try {
            blockingQueue.put(trackTimeInfo);
        } catch (InterruptedException e) {
            log.error("Ошибка при постановке объекта TrackTimeInfo в очередь для сохранения в БД: {}, причина: {}",
                    e.getMessage(), e.getCause());
        }
    }

}

