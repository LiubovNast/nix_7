package service;

import entity.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {

    private static final Logger log = LoggerFactory.getLogger(CSVParser.class);

    public Table read(String fileName) {
        Table table = new Table();
        List<String> header;
        List<String> rows = new ArrayList<>();
        String csvString;
        try {
            if (Files.notExists(Path.of(fileName))) {
                log.error("File not found");
                throw new RuntimeException();
            }
            csvString = Files.readString(Paths.get(fileName)).trim();
        } catch (IOException e) {
            log.error("Exception read file {}", fileName);
            throw new RuntimeException(e.getCause());
        }
        String[] csvStrings = csvString.split("\n");
        String[] temp = csvStrings[0].replaceAll("\r", "").trim().split(", ");
        header = Arrays.asList(temp);
        for (int i = 1; i < csvStrings.length; i++) {
            rows.add(csvStrings[i].replaceAll("\r", "").trim());
        }
        table.setHeader(header);
        table.setRows(rows);
        return table;
    }
}
