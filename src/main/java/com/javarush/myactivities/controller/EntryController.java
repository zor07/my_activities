package com.javarush.myactivities.controller;

import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.services.interfaces.ActivityService;
import com.javarush.myactivities.services.interfaces.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;
    private final ActivityService activityService;

    @Autowired
    public EntryController(EntryService entryService,
                           ActivityService activityService) {
        this.entryService = entryService;
        this.activityService = activityService;
    }

    @RequestMapping
    public String getEntries(Model model) {
        model.addAttribute("activityEntriesMap", entryService.getActivityEntriesMap());
        return "entries/list";
    }

    @RequestMapping(value = "/new")
    public String create(Model model){
        model.addAttribute("entry", new Entry());
        model.addAttribute("activities", activityService.getAll());
        return "entries/card";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        final Entry entry = entryService.getById(id);
        model.addAttribute("entry", entry);
        model.addAttribute("activities", activityService.getAll());
        return "entries/card";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Entry entry){
        entryService.save(entry);
        return "redirect:/entries";
    }

}
