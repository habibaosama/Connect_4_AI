package com.example.connect_4_ai.utilities;
import java.util.*;

public class Node {
    private Node parent;
    public Node chosenNode;
    public State state;
    private final List<Node> children;
    public int score;

    public Node(State state) {
        this.state = state;
        children = new ArrayList<>();
        this.score = 0;
    }

    public List<Node> expand() {
        for(int col = 0; col < 7; col++) {
            if(state.isValidColumn(col)) {
                boolean isOpponent = Util.getBit(state.board,63) == 0;
                State childState = new State(state.board);
                Node child = new Node(new State(Util.alternateBit(childState.applyChoice(col, isOpponent),63)));
                child.parent = this;
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

}
