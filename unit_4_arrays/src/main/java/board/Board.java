package board;

import javax.swing.*;
import java.awt.*;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    public void drawBoard() {
        JFrame frame;
        JPanel squares[][] = new JPanel[WIDTH + 1][HEIGHT + 1];
        frame = new JFrame("Chess");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(HEIGHT + 1, WIDTH + 1));

        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                squares[i][j] = new JPanel();

                if (j == 0 && i != 0)  squares[i][j].add(new JLabel(String.valueOf(HEIGHT + 1 - i)));
                else if (i == 0 && j != 0)  squares[i][j].add(new JLabel(String.valueOf((char)('a' + j - 1))));

                else if ((i + j) % 2 != 0) {
                    squares[i][j].setBackground(Color.black);
                } else {
                    squares[i][j].setBackground(Color.white);
                }
                frame.add(squares[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
