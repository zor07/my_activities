package com.javarush.myactivities.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Entry {

    private Long id;
    private String comment;
    private LocalDate date;
    private Activity activity;

}
