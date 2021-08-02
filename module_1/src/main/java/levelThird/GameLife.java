package levelThird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static console.InputConsole.getInt;

public class GameLife {

    private static int WIDTH;
    private static int HEIGHT;
    private static int AMOUNT;
    private static final int LIVE = 1;
    private static final int DEAD = 0;
    private static boolean isStopped = false;
    private static int[][] board;


    public static void startLife() {
        init();
        draw(board);
        play(board);
        System.out.println("Game over");
    }

    private static void init() {
        System.out.println("Enter Height of board:");
        HEIGHT = getInt();
        System.out.println("Enter Width of board:");
        WIDTH = getInt();
        System.out.println("Enter MAX_AMOUNT of live cell:");
        AMOUNT = getInt();

        board = new int[HEIGHT][WIDTH];
        int count = 0;
        Random percent = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (percent.nextInt(100) < AMOUNT && count <= AMOUNT) {
                    board[i][j] = LIVE;
                    count++;
                }
            }
        }
    }

    private static void play(int[][] board) {
        List<int[][]> lifeCycle = new ArrayList<>();
        lifeCycle.add(board);
        while (!isStopped) {
            int[][] newGeneration = getNewGeneration(lifeCycle.get(lifeCycle.size() - 1));
            if (!isAlive(newGeneration)) {
                isStopped = true;
            } else if (sameGeneration(newGeneration, lifeCycle.get(lifeCycle.size() - 1))) {
                isStopped = true;
            } else {
                for (int[][] gen : lifeCycle) {
                    if (sameGeneration(newGeneration, gen)) {
                        isStopped = true;
                    }
                }
            }
            lifeCycle.add(newGeneration);
            draw(newGeneration);
        }
    }

    private static boolean sameGeneration(int[][] lastGeneration, int[][] previousGeneration) {
        for (int i = 0; i < lastGeneration.length; i++) {
            for (int j = 0; j < lastGeneration[0].length; j++) {
                if (lastGeneration[i][j] != previousGeneration[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isAlive(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == LIVE) return true;
            }
        }
        return false;
    }

    private static int[][] getNewGeneration(int[][] board) {
        int[][] newLife = new int[HEIGHT][WIDTH];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == DEAD && countLiveNeighbours(board, i, j) == 3) {
                    newLife[i][j] = LIVE;
                }
                if (board[i][j] == LIVE) {
                    int liveNeighbours = countLiveNeighbours(board, i, j);
                    if (liveNeighbours == 2 || liveNeighbours == 3) {
                        newLife[i][j] = LIVE;
                    } else {
                        newLife[i][j] = DEAD;
                    }
                }
            }
        }
        return newLife;
    }

    private static void draw(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("==============");
        System.out.println();
    }

    private static int countLiveNeighbours(int[][] board, int indexI, int indexJ) {
        int count = 0;
        int[] neighbour;

        neighbour = getNeighbourCoordinates(board, indexI - 1, indexJ - 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI, indexJ - 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI + 1, indexJ - 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI - 1, indexJ);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI - 1, indexJ + 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI + 1, indexJ + 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI + 1, indexJ);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        neighbour = getNeighbourCoordinates(board, indexI, indexJ + 1);
        if (board[neighbour[0]][neighbour[1]] == 1) count++;

        return count;
    }

    private static int[] getNeighbourCoordinates(int[][] board, int x, int y) {
        int[] coordinates = new int[2];
        if (x < 0) coordinates[0] = board.length - 1;
        else if (x < board.length) coordinates[0] = x;
        if (y < 0) coordinates[1] = board[0].length - 1;
        else if (y < board[0].length) coordinates[1] = y;
        return coordinates;
    }
}
