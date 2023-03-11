package com.tripproject.totalCalories.dto.response;

import com.tripproject.totalCalories.domain.CalorieLevelType;
import com.tripproject.totalCalories.domain.TotalCalories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BasicTotalCaloriesResponse {
    private Long id;
    /** 총 칼로리(일단위) */
    private Integer totalCalories;
    /** 몸무게(일단위) */
    private double weight;
    /** 칼로리 량에따라 위험수준 */

    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private CalorieLevelType calorieLevelType;


    public BasicTotalCaloriesResponse(TotalCalories totalCalories) {
        this.id = totalCalories.getId();
        this.totalCalories = totalCalories.getTotalCalories();
        this.weight = totalCalories.getWeight();
        if(totalCalories.getCreatedDate() != null){
            this.createdDate =totalCalories.getCreatedDate();
        }
    }
}
