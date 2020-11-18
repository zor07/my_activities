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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
    public String getEntries(Model model, @RequestParam(value = "date", required = false) LocalDate date) {

        final LocalDate filterDate = Optional.ofNullable(date).orElse(LocalDate.now());
        final LocalDate filterNextDate = filterDate.plusDays(1);
        final LocalDate filterPrevDate = filterDate.minusDays(1);

        model.addAttribute("filterDate", filterDate);
        model.addAttribute("filterNextDate", filterNextDate);
        model.addAttribute("filterPrevDate", filterPrevDate);
        model.addAttribute("entries", entryService.getAllByDate(filterDate));
        return "entries/list";
    }

    @RequestMapping(value = "/new")
    public String create(Model model, @RequestParam(value = "date") LocalDate date){
        final Entry entry = new Entry();
        entry.setDate(date);
        model.addAttribute("date", date);
        model.addAttribute("entry", entry);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        return "redirect:/entries?date=" + entry.getDate().format(formatter);
    }

}
