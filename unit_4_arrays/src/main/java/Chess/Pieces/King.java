package Chess.Pieces;

import Chess.ChessPiece;
import Chess.Move;

public class King extends ChessPiece {

    public King(PieceColor color) {
        super(PieceType.King, color, validMoves(), false);
    }

    private static Move[] validMoves() {
        return new Move[]{new Move(1, 0), new Move(0, 1), new Move(-1, 0), new Move(0, -1),
                new Move(1, 1), new Move(1, -1), new Move(-1, 1), new Move(-1, -1)};
    }
}
