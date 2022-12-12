package com.eyazis.labs.lab1.service.parser.lexer;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RussianAlphabet implements Alphabet{

    private final String LETTERS =
            "А а Б б В в Г г Д д Е е Ё ё Ж ж З з И и Й й К к Л л М м " +
                    "Н н О о П п Р р С с Т т У у Ф ф Х х Ц ц Ч ч Ш ш Щ щ Ъ ъ Ы ы Ь ь Э э Ю ю Я я";

    @Override
    public Set<String> getLetters() {
        return Arrays.stream(
                LETTERS.split(" ")
        ).map(String::trim).collect(Collectors.toSet());
    }
}
