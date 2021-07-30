package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

public class Rook extends ChessPiece {

    public Rook(PieceColor color) {
        super(PieceType.Rook, color, validMoves(), true);
    }

    private static Move[] validMoves() {
        return new Move[]{new Move(1, 0), new Move(0, 1), new Move(-1, 0), new Move(0, -1)};
    }
}
