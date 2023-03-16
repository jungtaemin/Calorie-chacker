package com.tripproject.calorie.dto.response;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.upload.domain.Upload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CalorieResponse {
    /** id */
    private Long id;
    /** 음식 이름 */
    private String name;
    /** 칼로리 */
    private Integer calorie;
    /** 메모 */
    private String memo;

    /** img storeName */
    private String storeFileName;

    public CalorieResponse(Calorie calorie) {
        this.id = calorie.getId();
        this.name = calorie.getName();
        this.calorie = calorie.getCalorie();
        this.memo = calorie.getMemo();
        this.storeFileName = calorie.getUpload().getStoreFileName();
    }
}
