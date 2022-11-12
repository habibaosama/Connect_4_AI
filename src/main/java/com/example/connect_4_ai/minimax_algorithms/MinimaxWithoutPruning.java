package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.NodeState;
import com.example.connect_4_ai.utilities.Node;
import com.example.connect_4_ai.utilities.State;
import com.example.connect_4_ai.utilities.Util;


public class MinimaxWithoutPruning implements IMinimax {
    public int maxLevel;

    public char[][] Decision(long bitsBoard, int maxLevel) {
        this.maxLevel = maxLevel;
        long startTime = System.currentTimeMillis();
        char[][] res = maximize(bitsBoard, 0).getBoard();
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
        return res;
    }


    //r ->cpu
    public NodeState maximize(Long bitsBoard, int level) {
        System.out.println(maxLevel);
        if (level == maxLevel || EvaluationState.isTerminal(bitsBoard)) {
            int eval = Evaluation.evaluateScore(bitsBoard);
            //int eval = Evaluation.eval(board);
            System.out.println(maxLevel);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MIN_VALUE);
        for (long child : EvaluationState.getChildren(bitsBoard, 'r')) {
            NodeState childState = minimize(child, level + 1);
           // System.out.print("level " + level + " " + childState.score + " ");
            if (childState.score > state.score) {
                char[][] b = Util.longToChar2dArray(child);
                state = new NodeState(b, childState.score);
            }


        }
        return state;

    }

    public NodeState minimize(long bitsBoard, int level) {
        if (level == maxLevel || EvaluationState.isTerminal(bitsBoard)) {
            // int eval = Evaluation.eval(board, 'y');
            int eval = Evaluation.evaluateScore(bitsBoard);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MAX_VALUE);
        for (long child : EvaluationState.getChildren(bitsBoard, 'y')) {
           // long bit = Util.char2dArrayToLong(child);
            NodeState childState = maximize(child, level + 1);

           // System.out.print("level " + level + " " + childState.score + " ");

            if (childState.score < state.score) {
                char[][] b = Util.longToChar2dArray(child);
                state = new NodeState(b, childState.score);
            }

        }
        System.out.println();
        return state;

    }


}
