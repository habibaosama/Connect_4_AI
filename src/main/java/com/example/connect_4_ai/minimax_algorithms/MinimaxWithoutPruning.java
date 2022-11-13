package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Node;
import com.example.connect_4_ai.utilities.State;
import com.example.connect_4_ai.utilities.Util;


public class MinimaxWithoutPruning implements IMinimax {
    public int maxLevel;
    public Node root;

    public char[][] Decision(long bitsBoard, int maxLevel) {
        this.maxLevel = maxLevel;
        long startTime = System.currentTimeMillis();
        root = new Node(new State(bitsBoard));
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
        return Util.longToChar2dArray(maximize(root,0).chosenNode.state.board);
    }


    //r ->cpu
    public Node maximize(Node node, int level) {
        System.out.println(maxLevel);
        char[][] board = Util.longToChar2dArray(node.state.board);
        if (level == maxLevel || node.isTerminal()) {
            node.score = Evaluation.evaluateScore(node.state.board);
//            System.out.println(maxLevel);
            return node;
        }

        node.score = Integer.MIN_VALUE;
        for (Node child : node.expand()) {
            child = minimize(child, level + 1);
           // System.out.print("level " + level + " " + childState.score + " ");
            if (child.score > node.score) {
                node.score = child.score;
                node.chosenNode = child;
            }
        }
        return node;

    }

    public Node minimize(Node node, int level) {
        char[][] board = Util.longToChar2dArray(node.state.board);
        if (level == maxLevel || node.isTerminal()) {
            // int eval = Evaluation.eval(board, 'y');
            node.score = Evaluation.evaluateScore(node.state.board);
            return node;
        }

        node.score = Integer.MAX_VALUE;
        for (Node child : node.expand()) {
           // long bit = Util.char2dArrayToLong(child);
            child = maximize(child, level + 1);
           // System.out.print("level " + level + " " + childState.score + " ");
            if(child.score < node.score){
                node.score = child.score;
                node.chosenNode = child;
            }
        }
        return node;
    }

}
