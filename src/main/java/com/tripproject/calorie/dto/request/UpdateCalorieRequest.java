package com.tripproject.calorie.dto.request;

import com.tripproject.calorie.domain.Calorie;
import com.tripproject.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCalorieRequest {

    @NotBlank(message = "음식명은 필수입니다.")
    private String updateName;
    @NotNull(message = "칼로리는 필수입니다.")
    private Integer updateCalorie;
    private String updateMemo;
    private User user;
    /** 업로드 이미지 */
    private MultipartFile updateUploadImg;


}
