package com.javarush.myactivities.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder @AllArgsConstructor
@Getter
public class Entry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String comment;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
    private Activity activity;

    public Entry() {}

}
