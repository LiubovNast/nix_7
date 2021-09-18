package secondTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static console.Console.*;

public class UniqueName {
    private static final String FILE = "files/names.txt";
    private static final String STRING_SEPARATOR = "---------------------------------------------";
    private String names;
    private String[] arrayNames;

    public void start() {
        printMessage("======== Task #2 ========");
        printMessage("=========================");
        printMessage(STRING_SEPARATOR);
        printMessage("This task about getting unique name from list of names.");
        printMessage("Do you want get names from file: names.txt? Please, enter - 1.");
        printMessage("Do you want enter names in console?, Please, enter - 2.");
        printMessage(STRING_SEPARATOR);

        int input = getInt();
        switch (input) {
            case 1:
                try {
                    names = Files.readString(Paths.get(FILE));
                    arrayNames = names.split("\n");
                } catch (IOException e) {
                    printMessage(e.getMessage());
                }
                getAndPrintUniqueName(arrayNames);
                break;
            case 2:
                printMessage("Please, enter names with space:");
                names = getString();
                arrayNames = names.split(" ");
                getAndPrintUniqueName(arrayNames);
                break;
            default:
                printMessage("Please, enter correct number");
                start();
                break;
        }
    }

    private void getAndPrintUniqueName(String[] arrayNames) {
        Map<String, Long> mapNames = Arrays.stream(arrayNames)
                .collect(Collectors.groupingBy(name -> name, LinkedHashMap::new, Collectors.counting()));
        if (mapNames.entrySet().stream().anyMatch(name -> name.getValue() == 1)) {
            String uniqueName = mapNames.entrySet().stream()
                    .filter(name -> name.getValue() == 1).map(Map.Entry::getKey).findFirst().get();
            printMessage("Your first unique name is " + uniqueName);
        } else printMessage("We don't have unique name.");
    }
}
