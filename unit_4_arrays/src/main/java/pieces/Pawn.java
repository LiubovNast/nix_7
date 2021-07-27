package pieces;

public class Pawn extends Chesspiece{

    private static final String whitePawn = "♟";
    private static final String blackPawn = "♙";

    public static String getWhite() {
        return whitePawn;
    }

    public static String getBlack() {
        return blackPawn;
    }

    @Override
    void move() {

    }
}
