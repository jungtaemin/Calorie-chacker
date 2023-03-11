package com.tripproject.calorie.controller;

import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import com.tripproject.calorie.dto.request.SelectCalorieListRequest;
import com.tripproject.calorie.dto.response.ListCaloriePageResponse;
import com.tripproject.calorie.dto.response.ListCalorieResponse;
import com.tripproject.calorie.service.CalorieService;
import com.tripproject.user.application.port.in.response.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calorie")
public class CalorieRestController {

    private final CalorieService calorieService;

    @PostMapping("/save")
    public ResponseEntity<Void> createCaloriePost(CreateCalorieRequest createCalorieRequest, BindingResult bindingResult
            ,@AuthenticationPrincipal PrincipalDetails principal){
        createCalorieRequest.setUser(principal.getUser());
        CreateCalorieRequest returnCalorieRequest = calorieService.createTotalCalorieJoin(principal.getUser().getId(),createCalorieRequest);
        calorieService.createCaloriePost(returnCalorieRequest.toEntity());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCaloriePost(@RequestParam Long id){
        calorieService.deleteCaloriePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<ListCaloriePageResponse> calorieList(Pageable pageable,@AuthenticationPrincipal PrincipalDetails principal){

        ListCaloriePageResponse listCaloriePageResponse = calorieService.calorieList(principal.getUser().getId(), pageable);
        return ResponseEntity.ok(listCaloriePageResponse);
    }

    @PostMapping("/selectList")
    public ResponseEntity<ListCaloriePageResponse> selectCalorieList(SelectCalorieListRequest selectCalorieListRequest, @AuthenticationPrincipal PrincipalDetails principal){

        ListCaloriePageResponse listCaloriePageResponse = calorieService.selectCalorieList(principal.getUser().getId(),selectCalorieListRequest);
        return ResponseEntity.ok(listCaloriePageResponse);
    }
}
