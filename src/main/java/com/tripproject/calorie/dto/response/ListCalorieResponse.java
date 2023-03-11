package com.tripproject.calorie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ListCalorieResponse {

    private Long id;

    /** 음식 이름 */
    private String name;
    /** 칼로리 */
    private Integer calorie;
    /** 메모 */
    private String memo;

    public ListCalorieResponse(Long id, String name, Integer calorie, String memo) {
        this.id = id;
        this.name = name;
        this.calorie = calorie;
        this.memo = memo;
    }
}
