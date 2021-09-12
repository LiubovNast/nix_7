package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String getString() {
        String str = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            str = reader.readLine().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static int getInt() {
        try {
            int num = Integer.parseInt(getString());
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Write only number:");
        }
        return getInt();
    }
}
