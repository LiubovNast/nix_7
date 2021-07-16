package first.task;
import consolehelper.Console;

public class FirstTask {
    public static void main(String[] args) {
        Console console = new Console();
        String massege = "Maven compile";
        console.write(massege);
        console.writeUpperCase(massege);
        console.currentTime();
    }
}