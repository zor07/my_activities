package com.javarush.myactivities.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter
public class Activity {

    private Long id;
    private String name;
    private String description;
    private Project project;

}

