package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Util;

public class Evaluation {
    public static int ROWS = 6;
    public static int COLUMNS = 7;

    public static int evaluateScore(long bitsBoard) {
        // convert the long to char array
        char[][] board = Util.longToChar2dArray(bitsBoard);

        int score = 0;
        //Horizontal groups check
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS - 3; j++)
                score += HorizontalWindow(board, i, j);

        //Vertical groups check
        for (int j = 0; j < COLUMNS; j++)
            for (int i = 0; i < ROWS - 3; i++)
                score += verticalWindow(board, i, j);

        for (int i = 0; i < ROWS - 3; i++)
            for (int j = 0; j < COLUMNS - 3; j++) {
                score += DiagonalWindow(board, i, j, 'p');
                score += DiagonalWindow(board, i, COLUMNS - j - 1, 'n');
            }

        //If the pieces is close to the middle column so chance to win is more
        for (int i = 0; i < ROWS; i++) {
            if (board[i][COLUMNS / 2] == 'r')
                score += 2;
            if (board[i][(COLUMNS / 2) - 1] == 'r')
                score++;
            if (board[i][(COLUMNS / 2) + 1] == 'r')
                score++;
        }

        return score;
    }

    private static int HorizontalWindow(char[][] board, int startRow, int startColumn) {
        int cpuPieces = 0, oppPieces = 0, empty = 0;

        for (int i = startColumn; i < startColumn + 4; i++) {
            if (board[startRow][i] == 'r') {
                cpuPieces++;
            } else if (board[startRow][i] == 'y') {
                oppPieces++;
            } else {
                empty++;
            }
        }
        return calculatePointsWeight(cpuPieces, oppPieces, empty);
    }


    private static int verticalWindow(char[][] board, int startRow, int startColumn) {
        int cpuPieces = 0, oppPieces = 0, empty = 0;
        for (int i = startRow; i < startRow + 4; i++) {
            if (board[i][startColumn] == 'r')
                cpuPieces++;
            else if (board[i][startColumn] == 'y')
                oppPieces++;
            else
                empty++;
        }
        return calculatePointsWeight(cpuPieces, oppPieces, empty);
    }

    private static int DiagonalWindow(char[][] board, int startRow, int startColumn, char nd) {
        int CPUPieces = 0, oppPieces = 0, empty = 0;

        for (int i = startRow; i < startRow + 4; i++) {
            if (board[i][startColumn] == 'r')
                CPUPieces++;
            else if (board[i][startColumn] == 'y')
                oppPieces++;
            else
                empty++;
            //increment column here
            startColumn += nd == 'p' ? 1 : -1;
        }
        return calculatePointsWeight(CPUPieces, oppPieces, empty);
    }

    private static int calculatePointsWeight(int cpuPieces, int oppPieces, int empty) {
        if (cpuPieces == 4) {
            return 100;
        } else if (cpuPieces == 3 && empty == 1) {
            return 20;
        } else if (cpuPieces == 2 && empty == 2) {
            return 6;
        }
        if (oppPieces == 4) {
            return -150;
        } else if (oppPieces == 3 && empty == 1) {
            return -60;
        } else if (oppPieces == 2 && empty == 2) {
            return -6;
        }
        return 0;
    }



}
