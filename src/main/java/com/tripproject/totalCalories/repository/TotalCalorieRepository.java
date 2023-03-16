package com.tripproject.totalCalories.repository;

import com.tripproject.totalCalories.domain.TotalCalories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TotalCalorieRepository extends JpaRepository<TotalCalories,Long> {

    @Query("select t from TotalCalories t join t.calorie c join c.user u  where u.id = :id and t.createdDate = :date")
    Optional<TotalCalories> findByDate(@Param("id") Long id, @Param("date") LocalDate date);

    @Query("select DISTINCT t from TotalCalories t join t.calorie c join c.user u  where u.id = :id and t.createdDate between :start and :end")
    List<TotalCalories> findCalendarByStartEnd(@Param("id") Long id, @Param("start")LocalDate start, @Param("end")LocalDate end);

}
