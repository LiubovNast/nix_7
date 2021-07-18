package ua;

import ua.consolehelper.Console;

public class FirstTask {

    public static void main(String[] args) {
        Console console = new Console();
        String massege = "Console compile";
        console.write(massege);
        console.writeUpperCase(massege);
        console.currentTime();
    }
}