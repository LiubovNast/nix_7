package Chess;

public class Tile {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private ChessPiece piece;
    private final TileColor color;

    public enum TileColor {
        White, Black
    }

    public Tile(TileColor color) {
        this.color = color;
    }

    public Tile(TileColor color, ChessPiece piece) {
        this.color = color;
        this.piece = piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return this.piece;
    }

    public String getValue() {
        if (piece != null) {
            if (this.color == TileColor.Black) {
                if (piece.getColor() == ChessPiece.PieceColor.Black) {
                    return ANSI_RED + ANSI_BLACK + "[" + piece.getCharValue() + "]" + ANSI_RESET;
                } else return ANSI_RED + "[" + piece.getCharValue() + "]" + ANSI_RESET;
            } else if (piece.getColor() == ChessPiece.PieceColor.Black) {
                return ANSI_YELLOW + ANSI_BLACK + "[" + piece.getCharValue() + "]" + ANSI_RESET;
            } else return ANSI_YELLOW + "[" + piece.getCharValue() + "]" + ANSI_RESET;
        } else if (this.color == TileColor.Black) {
            return ANSI_RED + ANSI_WHITE + "[ ]" + ANSI_RESET;
        } else return ANSI_YELLOW + ANSI_WHITE + "[ ]" + ANSI_RESET;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void empty() {
        piece = null;
    }
}
