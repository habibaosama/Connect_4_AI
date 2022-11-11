package com.example.connect_4_ai.utilities;
import java.util.*;

public class Node {
    private Node parent;
    public State state;
    private List<Node> children;
    public int depth;

    public Node(Node parent, State state) {
        this.parent = parent;
        this.state = state;
        children = new ArrayList<>();
        this.depth = 0;
    }

    public void expand(){
        for(int col = 0; col <= 7; col++){
            if(state.isValidColumn(col)){
                Node child = new Node(this,new State(state.applyChoice(col)));
                children.add(child);
                child.depth = depth+1;
            }
        }
    }
}
