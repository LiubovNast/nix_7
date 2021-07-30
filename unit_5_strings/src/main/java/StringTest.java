import static console.InputConsole.*;
import static utils.ReverseString.reverse;

public class StringTest {

    public static void main(String[] args) {
        System.out.println("Write string for reverse:");

        String testString = getString();
        String reverseString = "";

        System.out.println("What do you want to do with this string?");
        System.out.println("1 - simple reverse, this string;");
        System.out.println("2 - simple reverse, each word in this string;");
        System.out.println("3 - reverse only some substring;");
        System.out.println("4 - reverse from/to index substring;");
        System.out.println("5 - reverse from/to char symbol.");

        int num = getInt();

        switch (num) {
            case 1:
                reverseString = reverse(testString, false);
                break;
            case 2:
                reverseString = reverse(testString, true);
                break;
            case 3:
                System.out.println("Write substring for reverse:");
                String substring = getString();
                reverseString = reverse(testString, substring);
                break;
            case 4:
                System.out.println("Write first index:");
                int first = getInt();
                System.out.println("Write last index:");
                int last = getInt();
                reverseString = reverse(testString, first, last);
                break;
            case 5:
                System.out.println("Write first symbol:");
                char charFirst = getChar();
                System.out.println("Write last symbol:");
                char charLast = getChar();
                reverseString = reverse(testString, charFirst, charLast);
                break;
            default:
                System.out.println("You didn't choose any method");
        }
        System.out.println("testString = " + testString);
        System.out.println("reverseString = " + reverseString);
    }
}
