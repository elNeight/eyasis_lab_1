package com.eyazis.labs.lab1.service.parser;

import com.eyazis.labs.lab1.service.parser.lexer.Alphabet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class WordParser implements Parser{

    private final Alphabet alphabet;
    private final Map<String, Integer> wordCounts = new HashMap<>();

    @Autowired
    public WordParser(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public Map<String, Integer> parse(String content) {

        Set<String> russianAlphabet = alphabet.getLetters();
        char[] letters = content.toCharArray();
        StringBuilder currentWord = new StringBuilder();

        for (char letter: letters) {

            String symbol = letter + "";

            if (russianAlphabet.contains(symbol)) {
                currentWord.append(symbol);
                continue;
            }

            if (currentWord.isEmpty()) continue;

            String wordToMap = currentWord.toString();
            wordCounts.merge(wordToMap, 1, Integer::sum);
            currentWord = new StringBuilder();
        }

        Map<String, Integer> toReturn = new HashMap<>(wordCounts);
        wordCounts.clear();

        return toReturn;
    }

}
