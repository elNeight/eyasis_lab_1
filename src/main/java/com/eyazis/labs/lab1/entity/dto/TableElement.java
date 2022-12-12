package com.eyazis.labs.lab1.entity.dto;

import com.eyazis.labs.lab1.entity.Word;
import lombok.Data;
import lombok.ToString;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
public class TableElement implements Comparable<TableElement>{

    private String fileName;
    private String path;
    private Set<String> words = new HashSet<>();
    private double generalCoefficient;


    @Override
    public int compareTo(TableElement o) {
        return Double.compare(generalCoefficient, o.generalCoefficient);
    }
}
