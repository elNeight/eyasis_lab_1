package com.eyazis.labs.lab1.service.parser.lexer;

import java.util.Set;

@FunctionalInterface
public interface Alphabet {
    Set<String> getLetters();
}
