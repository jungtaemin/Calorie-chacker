package com.tripproject.calorie.controller;

import com.tripproject.calorie.dto.request.CreateCalorieRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calorie")
public class CalorieController {

    @GetMapping("/save")
    public String createCalorie(Model model){
        model.addAttribute("createCalorieRequest",new CreateCalorieRequest());
        return"calorie/calorieCreateForm";
    }
}
