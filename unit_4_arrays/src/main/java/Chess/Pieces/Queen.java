package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

public class Queen extends ChessPiece {

    public Queen(PieceColor color) {
        super(PieceType.Queen, color, validMoves(), true);
    }

    private static Move[] validMoves() {
        return new Move[]{new Move(1, 0), new Move(0, 1), new Move(-1, 0), new Move(0, -1),
                new Move(1, 1), new Move(1, -1), new Move(-1, 1), new Move(-1, -1)};
    }
}
