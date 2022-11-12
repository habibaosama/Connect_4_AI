package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.NodeState;
import com.example.connect_4_ai.utilities.Node;

import java.util.ArrayList;
import java.util.List;

public class MinimaxWithPruning implements IMinimax{

    public char[][] Decision(char[][] board) {
        long startTime = System.currentTimeMillis();
        char[][] res = maximize(board, 7, Integer.MIN_VALUE, Integer.MAX_VALUE).getBoard();
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
        return res;
    }

    //r ->cpu
    public NodeState maximize(char[][] board, int level, int alpha, int beta) {
        if (level == 0 || EvaluationState.isTerminal(board)) {
            int eval = Evaluation.eval(board, 'r');
            //int eval = Evaluation.eval(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MIN_VALUE);
//        state.children = EvaluationState.getChildren(board,'r');
        for (NodeState child : EvaluationState.getChildren(board,'r')) {
            NodeState childState = minimize(child.board, level - 1, alpha, beta);

            if (childState.score > state.score)
                state = new NodeState(child.board, childState.score);

            if (state.score >= beta)
                break;
            if (state.score > alpha)
                alpha = state.score;

        }
        return state;

    }

    public NodeState minimize(char[][] board, int level, int alpha, int beta) {

        if (level == 0 || EvaluationState.isTerminal(board)) {
            int eval = Evaluation.eval(board, 'y');
            //int eval = Evaluation.eval(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MAX_VALUE);
        for (NodeState child : EvaluationState.getChildren(board, 'y')) {
            NodeState childState = maximize(child.board, level - 1, alpha, beta);
            if (childState.score < state.score)
                state = new NodeState(child.board, childState.score);
            if (state.score <= alpha)
                break;
            if (state.score < beta)
                beta = state.score;

        }
        return state;

    }
}
