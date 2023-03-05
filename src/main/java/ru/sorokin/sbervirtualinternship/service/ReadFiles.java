package ru.sorokin.sbervirtualinternship.service;

import ru.sorokin.sbervirtualinternship.exceptions.ReadFilesHandleException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {
    public List<String> getLinesFromFile(File file) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(file)));
            while (reader.ready()) {
                String line = reader.readLine().trim();
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new ReadFilesHandleException("Could not read data from file");
        }
    }
}
