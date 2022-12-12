package com.eyazis.labs.lab1.service.parser;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Parser {
    Map<String, Integer> parse(String content);
}
