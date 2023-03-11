package com.tripproject.calorie.domain;

import com.tripproject.shared.domain.BaseEntity;
import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *    칼로리 테이블
 *    회원이 칼로리 등록시 등록일 기준 칼로리 쌓임    ex) 2023-03-03에 등록시  칼로리들   다음달되면 그날 기준
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calorie extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "calorie_id")
    private Long id;

    /** 음식 이름 */
    private String name;
    /** 칼로리 */
    private Integer calorie;
    /** 메모 */
    private String memo;
    /** 회원 - 연관관계 매핑 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "total_calories_id")
    private TotalCalories totalCalories;

    public Calorie(String name, Integer calorie, String memo, User user,TotalCalories totalCalories) {
        this.name = name;
        this.calorie = calorie;
        this.memo = memo;
        this.user = user;
        setTotalCalories(totalCalories);
    }

    private void setTotalCalories(TotalCalories totalCalories){

        this.totalCalories = totalCalories;
        totalCalories.getCalorie().add(this);
    }
}
