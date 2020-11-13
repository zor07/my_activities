package com.javarush.myactivities.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Builder @AllArgsConstructor
@Getter
public class Activity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public Activity() {}

}

