package com.tripproject.totalCalories.dto.request;

import com.tripproject.totalCalories.domain.TotalCalories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTotalCalorieRequest {
    /** 총 칼로리(일단위) */
    private Integer totalCalories;
    /** 몸무게(일단위) */
    private double weight;

    public TotalCalories toEntity(){
       return TotalCalories.builder().totalCalories(totalCalories).weight(weight).build();
    }
}
