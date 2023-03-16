package com.tripproject.totalCalories.service;

import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.totalCalories.dto.response.BasicTotalCaloriesResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 1일부터 마지막 일까지 사용자에따라 저장안한 날이 있을것이고 그것을 채워주는 팩토리 입니다. */
@Component
public class CalendarFactory {

    private Map<Integer,BasicTotalCaloriesResponse> totalCaloriesCalendar;
    private boolean mapPutSwitch =false;

    /** 날짜중에 totalCalorie가 만들어있지 않은 날은 null값으로라도 list에 넣음 */
    public static Map<Integer,BasicTotalCaloriesResponse> calendarCreate(LocalDate start, LocalDate end, List<TotalCalories> totalCalories){
        //동시성 문제에서 안전하게 하기위해서 요청마다 초기화하게 짜놨습니다.
        CalendarFactory calendarFactory = new CalendarFactory();
        calendarFactory.totalCaloriesCalendar = new HashMap<>();
        for(Integer i=start.getDayOfMonth(); i<=end.getDayOfMonth(); i++)  {
            for(TotalCalories totalCalorie:totalCalories){
                if(totalCalorie.getCreatedDate().getDayOfMonth() == i){
                    calendarFactory.totalCaloriesCalendar.put(i,new BasicTotalCaloriesResponse(totalCalorie));
                    calendarFactory.changeSwitch();
                }else{
                    if(calendarFactory.isMapPutSwitch()){
                    }else{
                        calendarFactory.totalCaloriesCalendar.put(i,new BasicTotalCaloriesResponse(TotalCalories.builder().build()));
                    }

                }
            }
            calendarFactory.MapPutSwitchFalse();
        }
        return calendarFactory.getTotalCaloriesCalendar();
    }

    public static Map<Integer,BasicTotalCaloriesResponse> calendarNoSizeCreate(LocalDate start, LocalDate end){
        //동시성 문제에서 안전하게 하기위해서 요청마다 초기화하게 짜놨습니다.
        CalendarFactory calendarFactory = new CalendarFactory();
        calendarFactory.totalCaloriesCalendar = new HashMap<>();
        for(Integer i=start.getDayOfMonth(); i<=end.getDayOfMonth(); i++)  {

            calendarFactory.totalCaloriesCalendar.put(i,new BasicTotalCaloriesResponse(TotalCalories.builder().build()));

        }
        return calendarFactory.getTotalCaloriesCalendar();
    }

    private Map<Integer,BasicTotalCaloriesResponse> getTotalCaloriesCalendar(){
        return this.totalCaloriesCalendar;
    }


    private void changeSwitch(){
        if(this.mapPutSwitch == false){
            this.mapPutSwitch = true;
        }else{
            this.mapPutSwitch = false;
        }
    }

    public boolean isMapPutSwitch() {
        return mapPutSwitch;
    }

    public void MapPutSwitchFalse() {
        this.mapPutSwitch = false;
    }
}


