package com.alekseyz.testtask.tracktime.repository;

import com.alekseyz.testtask.tracktime.dto.IAgregateExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.dto.IGroupByAnnotationExecutionTimeMethod;
import com.alekseyz.testtask.tracktime.entity.TrackTimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackTimeInfoRepository extends JpaRepository<TrackTimeInfo, Long> {

    @Query(value = "select method_name methodname, " +
            "sum(extract(milliseconds from end_time-start_time)) sumexecutiontime, " +
            "avg(extract(milliseconds from end_time-start_time)) avgexecutiontime, " +
            "min(extract(milliseconds from end_time-start_time)) minexecutiontime, " +
            "max(extract(milliseconds from end_time-start_time)) maxexecutiontime, " +
            "count(method_name) runcount " +
            "from track_time_info group by method_name order by method_name ", nativeQuery = true)
    List<IAgregateExecutionTimeMethod> findAgregateExecutionMethod();

    @Query(value = "select case when method_name like 'sync%' then 'TrackTime'" +
            "when method_name like 'async%' then 'TrackAsyncTime' end methodannotation, " +
            "sum(extract(milliseconds from end_time-start_time)) sumexecutiontime, " +
            "avg(extract(milliseconds from end_time-start_time)) avgexecutiontime, " +
            "min(extract(milliseconds from end_time-start_time)) minexecutiontime, " +
            "max(extract(milliseconds from end_time-start_time)) maxexecutiontime, " +
            "count(method_name) runcount " +
            "from track_time_info group by methodannotation order by methodannotation", nativeQuery = true)
    List<IGroupByAnnotationExecutionTimeMethod> findGroupByAnnotationExecutionMethod();

}