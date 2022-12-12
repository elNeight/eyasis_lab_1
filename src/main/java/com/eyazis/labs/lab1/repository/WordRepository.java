package com.eyazis.labs.lab1.repository;

import com.eyazis.labs.lab1.entity.Document;
import com.eyazis.labs.lab1.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WordRepository extends JpaRepository<Word, Long> {
}
