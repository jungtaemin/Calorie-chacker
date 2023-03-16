package com.tripproject.calorie.controller;

import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import com.tripproject.calorie.dto.request.UpdateCalorieRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalorieController {

    @GetMapping("/calorie/save")
    public String createCalorie(Model model){
        model.addAttribute("createCalorieRequest",new CreateCalorieRequest());
        model.addAttribute("updateCalorieRequest",new UpdateCalorieRequest());
        return"calorie/calorieCreateForm";
    }


    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("createCalorieRequest",new CreateCalorieRequest());
        model.addAttribute("updateCalorieRequest",new UpdateCalorieRequest());
        return"calorie/calorieCreateForm";
    }
}
