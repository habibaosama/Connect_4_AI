package com.example.connect_4_ai;

import com.example.connect_4_ai.utilities.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeState {
    public char[][] board;
    public int row;
    public int score;
    final int LENGTH=6;
    final int WIDTH=7;
    public List<NodeState> children;
    public NodeState(){
        board=new char[LENGTH][WIDTH];
        children = new ArrayList<>();
        /*for(int i=0;i<LENGTH;i++)
            Arrays.fill(board[i], '_');*/
    }
    public NodeState(char[][] board, int score){
        this.board=board;
        this.score =score;
    }

    public char[][] getBoard(){
        return board;
    }

}
