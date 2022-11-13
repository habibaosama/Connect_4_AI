package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Node;

public class MinimaxWithPruning extends MiniMax {
    public Node fin;

    @Override
    protected Node maximize(Node node, int level) {
        return maximize(node, level, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //r ->cpu
    public Node maximize(Node node, int level, int alpha, int beta) {
        expandedNodes++;
        if (level == maxLevel || node.isTerminal()) {
            // int eval = Evaluation.eval(board, 'r');
            node.score = Evaluation.evaluateScore(node.state.board);
            fin = node;
            return node;
        }

        node.score = Integer.MIN_VALUE;
        for (Node child : node.expand()) {
            expandedNodes++;
            child = minimize(child, level + 1, alpha, beta);

            if (child.score > node.score) {
                node.score = child.score;
                node.chosenNode = child;
            }

            if (node.score >= beta)
                break;
            alpha = Math.max(alpha, node.score);
//            System.out.println("level " + level + " " + node.score + " ");
        }
        fin = node;
        return node;

    }

    public Node minimize(Node node, int level, int alpha, int beta) {

        expandedNodes++;
        if (level == maxLevel || node.isTerminal()) {
            // int eval = Evaluation.eval(board, 'y');
            node.score = Evaluation.evaluateScore(node.state.board);
            fin = node;
            return node;
        }

        node.score = Integer.MAX_VALUE;
        for (Node child : node.expand()) {
            expandedNodes++;
            child = maximize(child, level + 1, alpha, beta);

            if (child.score < node.score) {
                node.score = child.score;
                node.chosenNode = child;
            }
            if (node.score <= alpha)
                break;
            beta = Math.min(beta, node.score);
        }
        fin = node;
        return node;
    }
}
