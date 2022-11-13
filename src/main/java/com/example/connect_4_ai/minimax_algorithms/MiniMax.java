package com.example.connect_4_ai.minimax_algorithms;

import com.example.connect_4_ai.utilities.Node;
import com.example.connect_4_ai.utilities.State;
import com.example.connect_4_ai.utilities.Util;

public abstract class MiniMax {
    protected int maxLevel;
    protected Node root;

    public int Decision(long bitsBoard, int k) {
        this.maxLevel = k;
        long startTime = System.currentTimeMillis();
        root = new Node(new State(bitsBoard));
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
        return maximize(root, 0).getChosenCol();
//        return Util.longToChar2dArray(maximize(root, 0).chosenNode.state.board);
    }

    protected abstract Node maximize(Node node, int level);

}
