package com.javarush.myactivities.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode
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

