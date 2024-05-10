package com.alekseyz.testtask.tracktime.service;

import com.alekseyz.testtask.tracktime.entity.TrackTimeInfo;

public interface AsyncSaveService {
    void addTrackTimeInfoForSave(TrackTimeInfo trackTimeInfo);
}
