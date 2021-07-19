package home.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {
    public static void countNumbers() {
        int sum = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите строку содержащую цифры");

        try {
            String string = reader.readLine();
            String[] temp = string.split("");
            for (int i = 0; i < temp.length; i++) {
                try {
                    sum += Integer.parseInt(temp[i]);
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Сумма всех введенных цифр: " + sum);

    }
}
