package com.galvanize.moviesCheckpoint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findByTitle(String title);
    @Query(value = "SELECT * FROM lessons WHERE (delivered_on BETWEEN :date1 AND :date2)",nativeQuery = true)
    List<Lesson> findByDeliveredOnBetween(Date date1, Date date2);
}