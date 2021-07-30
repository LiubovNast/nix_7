package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

public class Knight extends ChessPiece {

    public Knight(PieceColor color) {
        super(PieceType.Knight, color, validMoves(), false);
    }

    private static Move[] validMoves() {
        return new Move[]{new Move(2, 1), new Move(1, 2),
                new Move(2, -1), new Move(-1, 2), new Move(2, -1), new Move(-1, 2),
                new Move(-2, 1), new Move(1, -2), new Move(-2, -1), new Move(-1, -2),
                new Move(-2, -1), new Move(-1, -2)};
    }
}
