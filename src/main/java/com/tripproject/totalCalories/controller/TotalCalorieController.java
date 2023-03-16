package com.tripproject.totalCalories.controller;

import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import com.tripproject.calorie.dto.request.UpdateCalorieRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/totalCalorie")
public class TotalCalorieController {

    @GetMapping("/calendar")
    public String createCalorie(Model model){
        model.addAttribute("createCalorieRequest",new CreateCalorieRequest());
        model.addAttribute("updateCalorieRequest",new UpdateCalorieRequest());
        return"calorie/totalDateCalorie";
    }
}
