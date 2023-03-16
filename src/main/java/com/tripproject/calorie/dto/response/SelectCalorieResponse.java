package com.tripproject.calorie.dto.response;

import com.tripproject.totalCalories.dto.response.BasicTotalCaloriesResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectCalorieResponse {

    private ListCaloriePageResponse listCaloriePageResponse;
    private BasicTotalCaloriesResponse basicTotalCaloriesResponse;
}
