package board;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final String[][] chessBoard = new String[HEIGHT + 1][WIDTH + 1];

    public Board() {
        chessBoard[0][0] = "";
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (j == 0 && i != 0)  chessBoard[i][j] = String.valueOf(HEIGHT + 1 - i);
                else if (i == 0 && j != 0)  chessBoard[i][j] = String.valueOf((char)('a' + j - 1));
                else if ((i + j) % 2 != 0) {
                    chessBoard[i][j] = "bl";
                } else {
                    chessBoard[i][j] = "wh";
                }
            }
        }
    }

    public void draw() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                System.out.printf("%2s ",chessBoard[i][j]);
            }
            System.out.println();
        }
    }
}
