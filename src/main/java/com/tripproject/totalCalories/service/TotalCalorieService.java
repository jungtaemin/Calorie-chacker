package com.tripproject.totalCalories.service;

import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.totalCalories.dto.request.YearMonthTotalCalorieRequest;
import com.tripproject.totalCalories.dto.response.BasicTotalCaloriesResponse;
import com.tripproject.totalCalories.repository.TotalCalorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TotalCalorieService {

    private final TotalCalorieRepository totalCalorieRepository;

    @Transactional
    public BasicTotalCaloriesResponse createTotalCalorie(final Long userId,final TotalCalories totalCalories){

        Optional<TotalCalories> resultDate = totalCalorieRepository.findByDate(userId,LocalDate.now());
        if(resultDate.isEmpty()){
            return new BasicTotalCaloriesResponse(totalCalorieRepository.save(totalCalories));
        }else{
            resultDate.ifPresent(cal -> cal.changeField(totalCalories.getTotalCalories(), totalCalories.getWeight()));
            return new BasicTotalCaloriesResponse(TotalCalories.builder().totalCalories(totalCalories.getTotalCalories()).weight(totalCalories.getWeight()).build());
        }
    }


    public BasicTotalCaloriesResponse getTodayTotalCalorie(final Long userId){
        Optional<TotalCalories> resultDate = totalCalorieRepository.findByDate(userId,LocalDate.now());
        if(resultDate.isEmpty()){
            return new BasicTotalCaloriesResponse(TotalCalories.builder().build());
        }else{
            return new BasicTotalCaloriesResponse(resultDate.get());
        }
    }


    public Map<Integer,BasicTotalCaloriesResponse> getTotalCalorieCalendar(final Long userId,final YearMonthTotalCalorieRequest yearMonthTotalCalorieRequest){
        yearMonthTotalCalorieRequest.createStartEndDate();
        List<TotalCalories> calendarByStartEnd = totalCalorieRepository.findCalendarByStartEnd(userId,yearMonthTotalCalorieRequest.getStartDate(),yearMonthTotalCalorieRequest.getEndDate());
        return CalendarFactory.calendarCreate(yearMonthTotalCalorieRequest.getStartDate(), yearMonthTotalCalorieRequest.getEndDate(), calendarByStartEnd);
    }

}
