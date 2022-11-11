package com.example.connect_4_ai.minimax_algorithms;
import com.example.connect_4_ai.NodeState;


public class MinimaxWithoutPruning  {

    public char[][] Decision(char[][] board ) {
        long startTime = System.currentTimeMillis();
        char[][] res = maximize(board, 0).getBoard();
        System.out.println( (System.currentTimeMillis() - startTime) + " ms");
        return res;
    }


//r ->cpu
    public NodeState maximize(char[][] board, int level){

        if (level ==4 || EvaluationState.isTerminal(board)) {
           int eval = Evaluation.eval(board,'r');
            //int eval = Evaluation.eval(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MIN_VALUE);
       for (char[][] child : EvaluationState.getChildren(board, 'r')) {
           NodeState childState = minimize(child, level + 1);

            if (childState.score > state.score)
                state = new NodeState(child, childState.score);

        }
        return state;

    }

    public NodeState minimize(char[][] board, int level){

        if (level == 4 || EvaluationState.isTerminal(board)) {
          int eval = Evaluation.eval(board,'y');
           // int eval = Evaluation.eval(board);
            return new NodeState(null, eval);
        }

        NodeState state = new NodeState(null, Integer.MAX_VALUE);
        for (char[][] child : EvaluationState.getChildren(board, 'y')) {
             NodeState childState = maximize(child, level + 1);
            if (childState.score < state.score)
                state = new NodeState(child, childState.score);

        }
        return state;

    }



}
