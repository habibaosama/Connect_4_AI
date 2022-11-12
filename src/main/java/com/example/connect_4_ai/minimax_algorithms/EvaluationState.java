package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class EvaluationState {
   // public static List<Integer> columns = new ArrayList<>();
    //need to be removed
    public static boolean isTerminal(char[][] board) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == '\u0000')
                    return false;
        return true;
    }

    public static List<char[][]> getChildren(long bitsBoard, char player) {
        char[][] board= Util.longToChar2dArray(bitsBoard);
        //columns = new ArrayList<>();
        List<char[][]> children = new ArrayList<>();
        for (int col = 0; col < board[0].length; col++) {
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][col] == '\u0000') {
                    char[][] child = copyBoard(board);
                    child[row][col] = player;
                   // columns.add(clm);
                    children.add(child);
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
