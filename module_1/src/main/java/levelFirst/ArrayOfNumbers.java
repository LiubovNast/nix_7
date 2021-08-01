package levelFirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static console.InputConsole.getString;

public class ArrayOfNumbers {

    public static void getUniqueNumberFromArray() {
        System.out.println("Input numbers to array, if you want to finish input - enter stop:");
        String input = getString();
        Map<Integer,Integer> array = new HashMap<>();
        List<Integer> numbers = new ArrayList<>();

        while (!input.equals("stop")) {
            int number = Integer.parseInt(input.trim());
            numbers.add(number);
            if (array.containsKey(number)) array.put(number, (array.get(number) + 1));
            else array.put(number, 1);
            input = getString();
        }
        int unique = 0;
        for (Integer index : array.keySet()) {
            if (array.get(index) == 1) {
                unique = index;
                break;
            }
        }

        System.out.println("Your array is - " + numbers.toString());
        System.out.println("Your unique number is - " + unique);
    }
}
