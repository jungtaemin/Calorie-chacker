package com.tripproject.totalCalories.domain;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.shared.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  총 칼로리 테이블
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "total_calories")
public class TotalCalories extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "total_calories_id")
    private Long id;
    /** 총 칼로리(일단위) */
    private Integer totalCalories;
    /** 몸무게(일단위) */
    private double weight;
    /** 칼로리 량에따라 위험수준 */
    @Enumerated(EnumType.STRING)
    private CalorieLevelType calorieLevelType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "totalCalories")
    private List<Calorie> calorie = new ArrayList<>();


    @Builder
    public TotalCalories(Integer totalCalories, double weight) {
        this.totalCalories = totalCalories;
        this.weight = weight;
    }

    public void changeField(Integer totalCalories,double weight){
        this.totalCalories = totalCalories;
        this.weight = weight;
    }
}
