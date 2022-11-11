package com.example.connect_4_ai;

public class State {
    public long board;
    State(){
        board=0;
    }

    public static String convertToString(long board){
        String connectBoard="000000000000000000000000000000000000000000000000000000000000000";
        if(Long.toBinaryString(board).length()<63){
            connectBoard = connectBoard.substring(0,63-Long.toBinaryString(board).length()+1) + Long.toBinaryString(board);
        }
        return connectBoard;
    }


}
