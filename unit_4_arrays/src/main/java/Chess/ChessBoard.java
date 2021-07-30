package Chess;

import Chess.Pieces.*;

import java.util.ArrayList;

public class ChessBoard {

    private static final int SIZE = 8;
    private final Tile[][] board;

    public ChessBoard() {
        board = new Tile[SIZE][SIZE];
        initializeBoard();
        fillBoard();
    }

    public static int getSIZE() {
        return SIZE;
    }

    public Tile[][] getBoardArray() {
        return board;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 != 0) board[i][j] = new Tile(Tile.TileColor.Black);
                else board[i][j] = new Tile(Tile.TileColor.White);
            }
        }
    }

    public Tuple getKingLocation(ChessPiece.PieceColor color) {
        Tuple location = new Tuple(-1, -1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (!board[y][x].isEmpty()) {
                    ChessPiece piece = board[y][x].getPiece();
                    if (piece.getColor() == color && piece instanceof King) {
                        location = new Tuple(x, y);
                    }
                }
            }
        }
        return location;
    }

    public Tuple[] getAllPiecesLocationForColor(ChessPiece.PieceColor color) {
        ArrayList<Tuple> locations = new ArrayList<>();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (!board[y][x].isEmpty() && board[y][x].getPiece().getColor() == color)
                    locations.add(new Tuple(x, y));
            }
        }
        return locations.toArray(new Tuple[0]);
    }

    public Tile getTileFromTuple(Tuple tuple) {
        return board[tuple.Y()][tuple.X()];
    }

    private void fillBoard() {
        for (int i = 0; i < SIZE; i++) {
            board[1][i].setPiece(new Pawn(ChessPiece.PieceColor.Black));
            board[6][i].setPiece(new Pawn(ChessPiece.PieceColor.White));
        }
        board[0][0].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[0][7].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[7][0].setPiece(new Rook(ChessPiece.PieceColor.White));
        board[7][7].setPiece(new Rook(ChessPiece.PieceColor.White));

        board[0][1].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[0][6].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[7][1].setPiece(new Knight(ChessPiece.PieceColor.White));
        board[7][6].setPiece(new Knight(ChessPiece.PieceColor.White));

        board[0][2].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[0][5].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[7][2].setPiece(new Bishop(ChessPiece.PieceColor.White));
        board[7][5].setPiece(new Bishop(ChessPiece.PieceColor.White));

        board[0][3].setPiece(new Queen(ChessPiece.PieceColor.Black));
        board[7][3].setPiece(new Queen(ChessPiece.PieceColor.White));

        board[0][4].setPiece(new King(ChessPiece.PieceColor.Black));
        board[7][4].setPiece(new King(ChessPiece.PieceColor.White));
    }
}
