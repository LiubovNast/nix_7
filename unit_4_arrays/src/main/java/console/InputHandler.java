package console;

import Chess.ChessBoard;
import Chess.Tuple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])([-])([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);

    public InputHandler() {
    }

    public Tuple parse(String val) {
        int x = Character.toLowerCase(val.charAt(0)) - 'a';
        int y = ChessBoard.getSIZE() - Integer.parseInt(String.valueOf(val.charAt(1)));
        return new Tuple(x, y);
    }

    public Tuple getFrom(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);
        return parse(coords);
    }

    public Tuple getTo(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(3);
        return parse(coords);
    }

    public boolean isValid(String val) {
        Matcher matcher = validMove.matcher(val);
        return matcher.matches();
    }
}
