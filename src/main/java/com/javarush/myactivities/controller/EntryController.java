package com.javarush.myactivities.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entries")
public class EntryController {

    @RequestMapping("/")
    public String getEntries() {
        return "entries/list";
    }

}
