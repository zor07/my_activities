package com.javarush.myactivities.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( uniqueConstraints= {
        @UniqueConstraint(columnNames = {"activity_id", "date"})
})
@Builder @AllArgsConstructor
@Getter @Setter
public class Entry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Entry() {}

}
