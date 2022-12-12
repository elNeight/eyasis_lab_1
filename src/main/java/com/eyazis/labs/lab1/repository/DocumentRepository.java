package com.eyazis.labs.lab1.repository;

import com.eyazis.labs.lab1.entity.Document;
import com.eyazis.labs.lab1.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "select count(*) from document d inner join word w on d.id = w.document_id where w.word = :word",
    nativeQuery = true)
    Long documentCountWithWord(@Param("word") String word);

}
