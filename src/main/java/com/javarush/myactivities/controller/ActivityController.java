package com.javarush.myactivities.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @RequestMapping("/")
    public String getActivities() {
        return "activities/list";
    }

}
