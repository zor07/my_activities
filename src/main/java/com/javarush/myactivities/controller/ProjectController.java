package com.javarush.myactivities.controller;

import com.javarush.myactivities.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping
    public String getProjects(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return "projects/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        projectService.delete(id);
        return "redirect:/projects";
    }


}
