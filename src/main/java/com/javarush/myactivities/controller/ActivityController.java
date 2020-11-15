package com.javarush.myactivities.controller;

import com.javarush.myactivities.services.interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("/")
    public String getActivities(Model model) {
        model.addAttribute("activities", activityService.getAll());
        return "activities/list";
    }

}
