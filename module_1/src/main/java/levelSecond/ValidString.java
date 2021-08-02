package levelSecond;

import static console.InputConsole.getString;

public class ValidString {

    public static void isValidString() {
        System.out.println("Enter your string, which has Brackets:");
        String str = getString();
        if (findAllBrackets(str))
            System.out.println("Your string is valid");
        else System.out.println("Your string is not valid");
    }

    private static boolean findAllBrackets(String str) {
        return findOneTypeOfBrackets(str, '(', ')') && findOneTypeOfBrackets(str, '{', '}')
                && findOneTypeOfBrackets(str, '[', ']');
    }

    private static boolean findOneTypeOfBrackets(String str, char openChar, char closeChar) {
        char[] arrayChar = str.toCharArray();
        int open = 0, close = 0;
        for (char c : arrayChar) {
            if (c == openChar) open++;
            if (c == closeChar) close++;
        }
        if (open != close) return false;
        else {
            for (int i = 0; i < arrayChar.length; i++) {
                if (arrayChar[i] == openChar) {
                    break;
                }
                if (arrayChar[i] == closeChar) {
                    return false;
                }
            }
            for (int i = arrayChar.length - 1; i >= 0; i--) {
                if (arrayChar[i] == openChar) {
                    for (int j = i; j < arrayChar.length; j++) {
                        if (arrayChar[j] == closeChar) {
                            arrayChar[i] = 0;
                            arrayChar[j] = 0;
                            String subStr = "";
                            for (int k = i; k < j; k++) {
                                subStr += arrayChar[k];
                            }
                            if (!findAllBrackets(subStr))
                                return false;
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}
