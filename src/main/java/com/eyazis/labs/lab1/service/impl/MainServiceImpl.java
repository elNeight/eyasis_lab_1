package com.eyazis.labs.lab1.service.impl;

import com.eyazis.labs.lab1.entity.Document;
import com.eyazis.labs.lab1.entity.Word;
import com.eyazis.labs.lab1.entity.dto.TableElement;
import com.eyazis.labs.lab1.repository.DocumentRepository;
import com.eyazis.labs.lab1.repository.WordRepository;
import com.eyazis.labs.lab1.service.MainService;
import com.eyazis.labs.lab1.service.arithmetics.Arithmetics;
import com.eyazis.labs.lab1.service.parser.Parser;
import com.eyazis.labs.lab1.service.reader.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final Parser parser;
    private final FileReader reader;
    private final Arithmetics arithmetics;
    private final DocumentRepository documentRepository;
    private final WordRepository wordRepository;


    @Override
    public void index() throws FileNotFoundException {
        saveAllDocuments();
        countCoefficient();
    }

    @Override
    public Set<TableElement> search(String filter) {

        Set<TableElement> elements = new TreeSet<>(Comparator.reverseOrder());

        Set<String> targets =
                Arrays.stream(filter.split(" "))
                        .collect(Collectors.toSet());

        documentRepository.findAll().forEach(document -> {
            Set<Word> words = document.getWords();
            TableElement element = new TableElement();
            for (String target : targets)
                words.forEach(word -> {
                    if (word.getWord().equals(target)) {
                        element.setFileName(document.getName());
                        element.setPath(document.getPath());
                        element.getWords().add(word.getWord());
                        element.setGeneralCoefficient(element.getGeneralCoefficient() + word.getCoefficient());
                    }
                });

            if (!element.getWords().isEmpty())
                elements.add(element);

        });

        return elements;
    }

    private void saveAllDocuments() throws FileNotFoundException {
        for (int i = 0; i < 7; i++) {

            File file = new File(
                    "C:\\Users\\serzh\\IdeaProjects\\lab1\\src\\main\\resources\\files\\" + (i + 1) + ".txt"
            );
            String content = reader.read(file);
            System.out.println("CONTENT : " + content);
            Map<String, Integer> wordCounts = parser.parse(content);

            Set<Word> words = new HashSet<>();

            Document document = new Document();
            document.setName(file.getName());
            document.setPath(file.getAbsolutePath());
            document.setText(content.toLowerCase());

            wordCounts.forEach((k, v) -> {
                Word word = new Word();
                word.setWord(k.toLowerCase());
                word.setRepetitions(v);
                word.setDocument(document);
                words.add(word);
            });

            document.setWords(words);

            documentRepository.save(document);

        }
    }

    private void countCoefficient() {
        List<Word> words = wordRepository.findAll();

        words.forEach(word -> {
            Double coefficient = arithmetics.getCoefficient(word);
            word.setCoefficient(coefficient);
            wordRepository.save(word);
        });
    }

}
