package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.Connect4Game;
import com.example.connect_4_ai.NodeState;
import com.example.connect_4_ai.utilities.Node;
import com.example.connect_4_ai.utilities.Util;

import java.util.Arrays;

public class MinimaxWithPruning implements IMinimax{

    public char[][] Decision(char[][] board) {
        long startTime = System.currentTimeMillis();
        long boardState = Util.char2dArrayToLong(board);
        char[][] res = maximize(boardState, 0, Integer.MIN_VALUE, Integer.MAX_VALUE).getBoard();
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
        return res;
    }

    //r ->cpu
    public NodeState maximize(long board, int level, int alpha, int beta) {
        if (level == 7 || EvaluationState.isTerminal(board)) {
           // int eval = Evaluation.eval(board, 'r');
            int eval = Evaluation.evaluateScore(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MIN_VALUE);
        for (long child : EvaluationState.getChildren(board, 'r')) {
            NodeState childState = minimize(child, level + 1, alpha, beta);

            if (childState.score > state.score) {
                char[][] b = Util.longToChar2dArray(child);
                state = new NodeState(b, childState.score);
            }
            if (state.score >= beta)
                break;
            if (state.score > alpha)
                alpha = state.score;

            System.out.println("level "+ level + " "+state.score+" ");

        }
        System.out.println();
        return state;

    }

    public NodeState minimize(long board, int level, int alpha, int beta) {

        if (level == 7 || EvaluationState.isTerminal(board)) {
           // int eval = Evaluation.eval(board, 'y');
            int eval = Evaluation.evaluateScore(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MAX_VALUE);
        for (long child : EvaluationState.getChildren(board, 'y')) {
            NodeState childState = maximize(child, level + 1, alpha, beta);
            if (childState.score < state.score) {
                char[][] b = Util.longToChar2dArray(child);
                state = new NodeState(b, childState.score);
            }
            if (state.score <= alpha)
                break;
            if (state.score < beta)
                beta = state.score;


        }

        return state;

    }
}
