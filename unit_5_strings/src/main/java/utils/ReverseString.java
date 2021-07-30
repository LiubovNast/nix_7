package utils;

public class ReverseString {

    public static String reverse(String src, boolean reverseEachWord) {
        String revStr = "";
        if (reverseEachWord) {
            String[] words = src.split(" ");
            for (String word : words) {
                revStr += reverse(word, false) + " ";
            }
            revStr.trim();
        } else {
            char[] array = src.toCharArray();
            for (int i = array.length - 1; i >= 0; i--) {
                revStr += array[i];
            }
        }
        return revStr;
    }

    public static String reverse(String src, String dest) {
        String revStr = "";
        String revDest = reverse(dest, false);
        String[] words = src.split(dest);
        for (int i = 0; i < words.length - 1; i++) {
            revStr += words[i] + revDest;
        }
        revStr += words[words.length - 1];
        return revStr;
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int temp = firstIndex;
            firstIndex = lastIndex;
            lastIndex = temp;
        }
        String dest = src.substring(firstIndex, lastIndex + 1);
        return reverse(src, dest);
    }

    public static String reverse(String src, char first, char last) {
        int firstIndex = src.indexOf(first);
        int lastIndex = src.indexOf(last, firstIndex + 1);
        String dest = src.substring(firstIndex, lastIndex + 1);
        return reverse(src, dest);
    }
}
