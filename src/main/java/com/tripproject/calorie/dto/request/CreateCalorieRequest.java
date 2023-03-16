package com.tripproject.calorie.dto.request;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.upload.domain.Upload;
import com.tripproject.user.domain.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCalorieRequest {
    @NotBlank(message = "음식명은 필수입니다.")
    private String name;
    @NotNull(message = "칼로리는 필수입니다.")
    private Integer calorie;
    private String memo;
    private User user;
    /** 업로드 이미지 */
    private MultipartFile uploadImg;

    private TotalCalories totalCalories;
    public  Calorie toEntity(){
        return new Calorie(this.name, this.calorie, this.memo, this.user, this.totalCalories);
    }
    public  Calorie toEntityAddImg(Upload upload){
        return new Calorie(this.name, this.calorie, this.memo, this.user, this.totalCalories,upload);
    }



    public void setUser(User user) {
        this.user = user;
    }
}
