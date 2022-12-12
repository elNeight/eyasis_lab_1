package com.eyazis.labs.lab1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"name"})
@ToString(of = {"name"})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;

    @Transient
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<Word> words;

}
