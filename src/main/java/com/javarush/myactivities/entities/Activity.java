package com.javarush.myactivities.entities;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class Activity {

    private Integer id;
    private String name;
    private String description;
    private Project project;

}

