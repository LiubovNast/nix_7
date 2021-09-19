package thirdTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static console.Console.printMessage;
import static thirdTask.service.CityService.findTheCheapestRoad;

public class TheCheapestRoad {

    private static final String FILE_INPUT = "files/input.txt";
    private static final String FILE_OUTPUT = "files/output.txt";

    public void start() {
        printMessage("======== Task #3 ========");
        printMessage("=========================");
        printMessage("This task about finding the cheapest road between cities.");
        String someTask = "";
        try {
            someTask = Files.readString(Paths.get(FILE_INPUT));
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
        String[] arrayRoadsCities = someTask.split("\n");
        String result = findTheCheapestRoad(arrayRoadsCities);
        try {
            if (Files.notExists(Path.of(FILE_OUTPUT))) {
                Files.createFile(Path.of(FILE_OUTPUT));
            }
            Files.writeString(Paths.get(FILE_OUTPUT), result);
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
        printMessage("Result of this task you can find in: files/output.txt.");
    }
}
