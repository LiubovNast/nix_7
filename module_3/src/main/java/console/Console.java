package console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static final Logger LOG = LoggerFactory.getLogger(Console.class);

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String getString() {
        String str = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            str = reader.readLine().trim();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return str;
    }

    public static int getInt() {
        try {
            int num = Integer.parseInt(getString());
            return num;
        } catch (NumberFormatException e) {
            LOG.error(e.getMessage());
            System.out.println("Write only number:");
        }
        return getInt();
    }
}
