package com.example.connect_4_ai.utilities;
import java.util.*;

public class Node {
    private Node parent;
    public State state;
    private List<Node> children;
    public int depth;

    public Node( State state) {
        //this.parent = parent;
        this.state = state;
        children = new ArrayList<>();
        this.depth = 0;
    }

    public void expand(){
        for(int col = 0; col <= 7; col++){
            if(state.isValidColumn(col)){
                Node child = new Node(new State(Util.alternateBit(state.applyChoice(col,Util.getBit(state.board,63) == 0),63)));
                children.add(child);
                child.depth = depth+1;
            }
        }
    }
    public boolean isTerminal(long boardState) {
        char[][] b = Util.longToChar2dArray(boardState);
        for (int i = 0; i <7; i++){
            for (int j=5;j>=0;j--) {
                if (b[j][i]=='\u0000')
                    return true;
            }
        }
        return false;
    }
}
