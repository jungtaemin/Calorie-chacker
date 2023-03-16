package com.tripproject.calorie.controller;

import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import com.tripproject.calorie.dto.request.SelectCalorieListRequest;
import com.tripproject.calorie.dto.response.CalorieResponse;
import com.tripproject.calorie.dto.response.ListCaloriePageResponse;
import com.tripproject.calorie.dto.response.ListCalorieResponse;
import com.tripproject.calorie.dto.response.SelectCalorieResponse;
import com.tripproject.calorie.service.CalorieService;
import com.tripproject.exception.NoSuchUserException;
import com.tripproject.totalCalories.dto.response.BasicTotalCaloriesResponse;
import com.tripproject.totalCalories.service.TotalCalorieService;
import com.tripproject.upload.domain.Upload;
import com.tripproject.upload.service.UploadService;
import com.tripproject.user.application.port.in.response.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calorie")
public class CalorieRestController {

    private final CalorieService calorieService;
    private final TotalCalorieService totalCalorieService;
    private final UploadService uploadService;


    @PostMapping("/save")
    public ResponseEntity<Void> createCaloriePost(@Validated CreateCalorieRequest createCalorieRequest, BindingResult bindingResult
            , @AuthenticationPrincipal PrincipalDetails principal) throws IOException {

        userCheckValidation(principal);
        createCalorieRequest.setUser(principal.getUser());
        CreateCalorieRequest returnCalorieRequest = calorieService.createTotalCalorieJoin(principal.getUser().getId(),createCalorieRequest);
        if(!createCalorieRequest.getUploadImg().isEmpty()){
            Upload upload = uploadService.saveUploadImg(createCalorieRequest.getUploadImg());
            calorieService.createCaloriePost(returnCalorieRequest.toEntityAddImg(upload));
        }else{
            calorieService.createCaloriePost(returnCalorieRequest.toEntity());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCaloriePost(@RequestParam Long id){
        calorieService.deleteCaloriePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<ListCaloriePageResponse> calorieList(Pageable pageable,@AuthenticationPrincipal PrincipalDetails principal){
        userCheckValidation(principal);
        ListCaloriePageResponse listCaloriePageResponse = calorieService.calorieList(principal.getUser().getId(), pageable);
        return ResponseEntity.ok(listCaloriePageResponse);
    }

    @PostMapping("/selectList")
    public ResponseEntity<SelectCalorieResponse> selectCalorieList(SelectCalorieListRequest selectCalorieListRequest, @AuthenticationPrincipal PrincipalDetails principal){
        userCheckValidation(principal);
        ListCaloriePageResponse listCaloriePageResponse = calorieService.selectCalorieList(principal.getUser().getId(),selectCalorieListRequest);
        BasicTotalCaloriesResponse customTotalCalorie = totalCalorieService.getCustomTotalCalorie(principal.getUser().getId(), selectCalorieListRequest);
        return ResponseEntity.ok( new SelectCalorieResponse(listCaloriePageResponse,customTotalCalorie));
    }

    @GetMapping("/detail")
    public ResponseEntity<CalorieResponse> calorieDetail(@AuthenticationPrincipal PrincipalDetails principal,@RequestParam Long id){
        CalorieResponse calorieResponse = calorieService.calorieDetail(id);
        return ResponseEntity.ok(calorieResponse);
    }


    private void userCheckValidation(PrincipalDetails principal) {
        if(principal == null){
            throw new NoSuchUserException("회원 정보가 없습니다.로그인 해 주세요.");
        }
    }


}
