package com.eyazis.labs.lab1.service.arithmetics;

import com.eyazis.labs.lab1.entity.Word;
import com.eyazis.labs.lab1.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Arithmetics {

    private final DocumentRepository documentRepository;

    public Double getCoefficient(Word word) {

        long count = documentRepository.count();
        long countWithWord = documentRepository.documentCountWithWord(word.getWord());
        word.setDocumentsCount((int) countWithWord);

        double inversionQuantity = Math.log((double) count / countWithWord);
        double quantityOfWordInDocument = word.getRepetitions();

        return inversionQuantity * quantityOfWordInDocument;
    }

}
