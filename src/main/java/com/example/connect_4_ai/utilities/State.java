package com.example.connect_4_ai.utilities;

public class State {


    // coln 0 -> 0 - 5
    // coln 1 -> 6 - 11
    // coln 2 -> 12 - 17
    // coln 3 -> 18 - 23

    // coln 0 last row -> 42 - 44
    // coln 1 last row -> 45 - 47
    // coln 2 last row -> 48 - 50
    // coln 3 last row -> 51 - 53


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
            return Util.clearBit(board, colBitPos + Util.getValue(board,lastRowBitPos,lastRowBitPos+2));
        return Util.setBit(board,colBitPos + Util.getValue(board,lastRowBitPos,lastRowBitPos+2));
    }

}
