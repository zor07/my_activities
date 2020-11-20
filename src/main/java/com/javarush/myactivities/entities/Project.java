package com.javarush.myactivities.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Builder @AllArgsConstructor
@Getter @Setter
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Project () {}

}