package home.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {

    public static void sortAndCountLetters() {
        int[] letters = new int[(int) Character.MAX_VALUE];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите строку содержащую буквы");
        try {
            String string = reader.readLine();
            char[] temp = string.toLowerCase().toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] <= 'z' && temp[i] >= 'a') {
                    letters[temp[i]]++;
                }
                if (temp[i] <= 'я' && temp[i] >= 'а') {
                    letters[temp[i]]++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int j = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            if (letters[i] != 0) {
                j++;
                System.out.println(j + ". " + (char) i + " = " + letters[i]);
            }
        }
        for (int i = 'а'; i <= 'я'; i++) {
            if (letters[i] != 0) {
                j++;
                System.out.println(j + ". " + (char) i + " = " + letters[i]);
            }
        }
    }
}
