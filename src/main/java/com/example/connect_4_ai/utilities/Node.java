package com.example.connect_4_ai.utilities;

import java.util.*;

public class Node {
    private Node parent;
    public Node chosenNode;
    public State state;
    public List<Node> children;
    public int score;
    public int col;

    public Node(State state) {
        this.state = state;
        children = new ArrayList<>();
        this.score = 0;
    }

    public List<Node> expand() {
        for (int col = 0; col < 7; col++) {
            if (state.isValidColumn(col)) {
                boolean isOpponent = Util.getBit(state.board, 63) == 0;
                State childState = new State(state.board);
                Node child = new Node(new State(Util.alternateBit(childState.applyChoice(col, isOpponent), 63)));
                child.parent = this;
                child.col = col;
                children.add(child);
            }
        }
        return children;
    }

    public boolean isTerminal() {
        int offset = 42;
        for (int i = 0; i < 7; i++) {
            if (Util.getValue(state.board, offset, offset + 2) > 0)
                return false;
            offset += 3;
        }
        return true;
    }

    public int getChosenCol() {
        if (chosenNode == null)
            return -1;
        int offset = 42;
        for (int i = 0; i < 7; i++) {
            if (Util.getValue(chosenNode.state.board, offset, offset + 2) !=
                    Util.getValue(this.state.board, offset, offset + 2))
                return i;
            offset += 3;
        }
        return -1;
    }

}
