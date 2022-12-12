package com.eyazis.labs.lab1.service;

import com.eyazis.labs.lab1.entity.dto.TableElement;

import java.io.FileNotFoundException;
import java.util.Set;

public interface MainService {

    void index() throws FileNotFoundException;

    Set<TableElement> search(String filter);

}
