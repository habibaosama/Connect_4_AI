package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class EvaluationState {
   // public static List<Integer> columns = new ArrayList<>();
    //need to be removed
    public static boolean isTerminal(long board) {
        char[][] boardState = Util.longToChar2dArray(board);
        for (int i = 0; i < boardState.length; i++)
            for (int j = 0; j < boardState[0].length; j++)
                if (boardState[i][j] == '\u0000')
                    return false;
        return true;
    }

    public static List<Long> getChildren(long bitsBoard, char player) {
        char[][] board= Util.longToChar2dArray(bitsBoard);
        //columns = new ArrayList<>();
        List<Long> children = new ArrayList<>();
        for (int col = 0; col < board[0].length; col++) {
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][col] == '\u0000') {
                    char[][] child = copyBoard(board);
                    child[row][col] = player;
                   // columns.add(clm);
                   // Util.char2dArrayToLong(child);
                    children.add(Util.char2dArrayToLong(child));
                    break;
                }
            }
        }
        return children;
    }
    public static char[][] copyBoard(char[][] board) {
        char[][] child = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            child[i] = board[i].clone();
        }
        return child;
    }


}
