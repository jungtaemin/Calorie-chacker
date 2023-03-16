package com.tripproject.calorie.repository;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface CalorieRepository extends JpaRepository<Calorie,Long> {

      @Query("select c from Calorie c join c.user u  where u.id = :id and c.createdDate = :date")
      Page<Calorie> findByUserId(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

      @Query("select c from Calorie c left join fetch c.upload where c.id = :id")
      Optional<Calorie> findDetailById(@Param("id") Long id);
}
