package com.eyazis.labs.lab1.service.reader;

import java.io.File;
import java.io.FileNotFoundException;

@FunctionalInterface
public interface FileReader {
    String read(File file) throws FileNotFoundException;
}
