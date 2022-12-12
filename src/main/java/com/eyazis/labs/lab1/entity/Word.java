package com.eyazis.labs.lab1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = {"word"})
@ToString(of = {"word"})
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;

    @ManyToOne(cascade = CascadeType.ALL)
    private Document document;

    private Integer repetitions;
    private Integer documentsCount;
    private Double coefficient;

}
