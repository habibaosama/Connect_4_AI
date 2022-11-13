package com.example.connect_4_ai.utilities;

public class State {

    public long board;

    public State(long board) {
        this.board = board;
    }

    public boolean isValidColumn(int colIndex) {
        int lastRowBitPos = 42 + colIndex * 3;
        return Util.isValid(board,lastRowBitPos,lastRowBitPos+2);
    }

    public long applyChoice(int colIndex, boolean isOpponent) {
        int colBitPos = colIndex * 6;
        int lastRowBitPos = 42 + colIndex * 3;
        board = Util.subtractOne(board,lastRowBitPos,lastRowBitPos+2);
        if(isOpponent)
            return board = Util.clearBit(board, colBitPos + Util.getValue(board, lastRowBitPos, lastRowBitPos + 2));
        return board = Util.setBit(board,colBitPos + Util.getValue(board,lastRowBitPos,lastRowBitPos+2));
    }

}
