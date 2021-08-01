package levelFirst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static console.InputConsole.getString;

public class MoveOfKnight {

    private final static Pattern validMove = Pattern.compile("([a-zA-Z]\\d+)([-])([a-zA-Z]\\d+)", Pattern.CASE_INSENSITIVE);

    private final int x;
    private final int y;

    private MoveOfKnight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private static boolean isValid(String val) {
        Matcher matcher = validMove.matcher(val);
        return matcher.matches();
    }

    private static int[] getFrom(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);
        return parse(coords);
    }

    private static int[] getTo(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(3);
        return parse(coords);
    }

    private static int[] parse(String val) {
        int x = Character.toLowerCase(val.charAt(0)) - 'a';
        int y = Integer.parseInt(String.valueOf(val.charAt(1)));
        return new int[]{x, y};
    }

    public static void canKnightMove() {
        System.out.println("Enter move for Knight (eg. A2-B4): ");
        String input = getString();
        if (!isValid(input)) {
            System.out.println("Invalid input!");
            System.out.println("Valid input is in form: A2-B4");
            canKnightMove();
        } else {
            int[] from = getFrom(input);
            int[] to = getTo(input);

            int xMove = to[0] - from[0];
            int yMove = to[1] - from[1];

            boolean isMove = false;
            for (MoveOfKnight move : validMoves()) {
                if (move.x == xMove && move.y == yMove) {
                    isMove = true;
                    break;
                }
            }
            if (isMove) System.out.println("Knight can move");
            else System.out.println("Knight cant move");
        }
    }

    private static MoveOfKnight[] validMoves() {
        return new MoveOfKnight[]{new MoveOfKnight(2, 1), new MoveOfKnight(1, 2),
                new MoveOfKnight(2, -1), new MoveOfKnight(-1, 2), new MoveOfKnight(2, -1), new MoveOfKnight(-1, 2),
                new MoveOfKnight(-2, 1), new MoveOfKnight(1, -2), new MoveOfKnight(-2, -1), new MoveOfKnight(-1, -2),
                new MoveOfKnight(-2, -1), new MoveOfKnight(-1, -2)};
    }
}
