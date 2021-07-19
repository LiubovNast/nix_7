package home.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {
    public static void endOfLessons() {
        final int startFistLesson = 9;
        final int smallBreak = 5;
        final int longBreak = 15;
        final int oneHour = 60;
        final int oneLesson = 45;
        int hours, minutes, totalTime, numberOfLesson = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите номер урока от 1 до 10");
        try {
            numberOfLesson = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalTime = oneLesson * numberOfLesson + (numberOfLesson - 1) / 2 * (longBreak + smallBreak) + (numberOfLesson - 1) % 2 * smallBreak;
        hours = totalTime / oneHour + startFistLesson;
        minutes = totalTime % oneHour;
        System.out.println("Урок №" + numberOfLesson + " заканчивается в " + hours + " " + minutes);
    }
}
