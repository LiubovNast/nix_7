package board;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final String[][] chessBoard = new String[HEIGHT + 1][WIDTH + 1];
    private List<Chesspiece> whites;
    private List<Chesspiece> blacks;

    public List<Chesspiece> getWhites() {
        return whites;
    }

    public List<Chesspiece> getBlacks() {
        return blacks;
    }

    public static String[][] getChessBoard() {
        return chessBoard;
    }

    public Board() {
        whites = Arrays.asList(new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook(),
                new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn());

        blacks = Arrays.asList(new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook(),
                new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn());
    }

    public void draw() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (i == 0) System.out.printf("%-3s ", chessBoard[i][j]);
                else System.out.printf("%3s", chessBoard[i][j]);
            }
            System.out.println();
        }
    }

    public void startGame() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (j == 0 && i == 0) chessBoard[0][0] = "  ";
                else if (j == 0) chessBoard[i][j] = String.valueOf(HEIGHT + 1 - i);
                else if (i == 0) chessBoard[i][j] = String.valueOf((char) ('a' + j - 1));
                else if ((i + j) % 2 != 0) {
                    chessBoard[i][j] = "";
                } else {
                    chessBoard[i][j] = "";
                }
            }
        }
        chessBoard[8][1] = Rook.getWhite();
        chessBoard[8][2] = Knight.getWhite();
        chessBoard[8][3] = Bishop.getWhite();
        chessBoard[8][4] = Queen.getWhite();
        chessBoard[8][5] = King.getWhite();
        chessBoard[8][6] = Bishop.getWhite();
        chessBoard[8][7] = Knight.getWhite();
        chessBoard[8][8] = Rook.getWhite();
        for (int i = 1; i < chessBoard.length; i++) {
            chessBoard[7][i] = Pawn.getWhite();
        }

        chessBoard[1][1] = Rook.getBlack();
        chessBoard[1][2] = Knight.getBlack();
        chessBoard[1][3] = Bishop.getBlack();
        chessBoard[1][4] = Queen.getBlack();
        chessBoard[1][5] = King.getBlack();
        chessBoard[1][6] = Bishop.getBlack();
        chessBoard[1][7] = Knight.getBlack();
        chessBoard[1][8] = Rook.getBlack();
        for (int i = 1; i < chessBoard.length; i++) {
            chessBoard[2][i] = Pawn.getBlack();
        }
    }
}
