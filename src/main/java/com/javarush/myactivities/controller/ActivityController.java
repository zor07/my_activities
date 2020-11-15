package com.javarush.myactivities.controller;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.services.interfaces.ActivityService;
import com.javarush.myactivities.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final ProjectService projectService;

    @Autowired
    public ActivityController(ActivityService activityService,
                              ProjectService projectService) {
        this.activityService = activityService;
        this.projectService = projectService;
    }

    @RequestMapping
    public String getActivities(Model model) {
        model.addAttribute("activities", activityService.getAll());
        return "activities/list";
    }

    @RequestMapping(value = "/new")
    public String create(Model model){
        model.addAttribute("activity", new Activity());
        model.addAttribute("projects", projectService.getAll());
        return "activities/card";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        final Activity activity = activityService.getById(id);
        model.addAttribute("activity", activity);
        model.addAttribute("projects", projectService.getAll());
        return "activities/card";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Activity activity){
        activityService.save(activity);
        return "redirect:/activities";
    }

    @RequestMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Long id) {
        activityService.delete(id);
        return "redirect:/activities";
    }

}
