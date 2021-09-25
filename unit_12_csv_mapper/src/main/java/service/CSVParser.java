package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVParser {

    public static String[] read(String fileName) {
        String csvString = "";
        try {
            if (Files.notExists(Path.of(fileName))) {
                return null;
            }
            csvString = Files.readString(Paths.get(fileName)).trim();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String[] csvStrings = csvString.split("\n");
        return csvStrings;
    }
}
