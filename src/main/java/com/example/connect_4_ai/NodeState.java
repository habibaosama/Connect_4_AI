package com.example.connect_4_ai;

public class NodeState {
    char[][] board;
    public int score;
    final int LENGTH=6;
    final int WIDTH=7;
    NodeState(){
        board=new char[LENGTH][WIDTH];
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
