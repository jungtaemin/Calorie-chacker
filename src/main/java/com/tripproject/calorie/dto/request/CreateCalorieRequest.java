package com.tripproject.calorie.dto.request;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.totalCalories.domain.TotalCalories;
import com.tripproject.user.domain.User;
import lombok.*;

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
    private TotalCalories totalCalories;
    public  Calorie toEntity(){
        return new Calorie(this.name, this.calorie, this.memo, this.user, this.totalCalories);
    }

    public void setUser(User user) {
        this.user = user;
    }

}
