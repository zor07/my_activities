package com.javarush.myactivities.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @RequestMapping("/")
    public String getProjects() {
        return "projects/list";
    }

}
