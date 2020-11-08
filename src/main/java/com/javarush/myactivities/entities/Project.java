package com.javarush.myactivities.entities;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class Project {

    private Long id;
    private String name;
    private String description;

}