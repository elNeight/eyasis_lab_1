package com.eyazis.labs.lab1.service.reader;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class FileReaderImpl implements FileReader{
    @Override
    public String read(File file) throws FileNotFoundException {

        System.out.println(file.getAbsolutePath());

        Scanner scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();

        while (scanner.hasNextLine())
            content.append(scanner.nextLine()).append("\n");

        return content.toString();
    }
}
