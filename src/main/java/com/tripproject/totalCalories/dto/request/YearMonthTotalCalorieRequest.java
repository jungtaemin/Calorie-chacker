package com.tripproject.totalCalories.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YearMonthTotalCalorieRequest {
    private int viewYear;
    private int viewMonth;

    private LocalDate startDate;
    private LocalDate endDate;
    
    /** 년도와 월로 시작일과 끝일 구하는 로직 */
    public void createStartEndDate(){
        startDate = LocalDate.of(viewYear,viewMonth,1);
        endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
    }
}
