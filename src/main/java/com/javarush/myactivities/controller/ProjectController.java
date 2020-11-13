package com.javarush.myactivities.controller;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/new")
    public String create(Model model) {
        model.addAttribute("project", new Project());
        return "projects/card";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        final Project project = projectService.getById(id);
        model.addAttribute("project", project);
        return "projects/card";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Project project){
        projectService.save(project);
        return "redirect:/projects";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        projectService.delete(id);
        return "redirect:/projects";
    }


}
