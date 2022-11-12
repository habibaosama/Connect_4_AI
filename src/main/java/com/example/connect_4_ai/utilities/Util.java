package com.example.connect_4_ai.utilities;

public class Util {

    public static long setBit(long number, int index) {
        number |= 1L << index;
        return number;
    }

    public static long clearBit(long number, int index){
        number &= ~(1L << index);
        return number;
    }

    public static int getBit(long number, int index){
       return (int) ((number >> index) & 1);
    }

    public static long alternateBit(long number, int index){
        if(getBit(number,index) == 1)
            return clearBit(number,index);
        return setBit(number,index);
    }

    public static boolean isValid(long number, int from, int to){
      for(int i = from; i <= to; i++){
          if(getBit(number,i) == 1)
             return false;
      }
        return true;
    }

    public static int getValue(long number, int from, int to){
        int counter = 0, value = 0;
        for(int i = from; i <= to; i++){
            if(getBit(number,i) == 1)
                value += Math.pow(2,counter);
            counter++;
        }
        return value;
    }
    public static long subtractOne(long number, int from, int to){
        int rightMostOne = from;
        while (from <= to && getBit(number,rightMostOne) != 1)
            rightMostOne++;
        number = clearBit(number,rightMostOne);
        rightMostOne--;
        while(rightMostOne >= from){
            number = setBit(number, rightMostOne);
            rightMostOne--;
        }
        return number;
    }

    public static long char2dArrayToLong(char[][] boardChar) {
        long board = 0;
        int[] lastRowIndices=new int[7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (boardChar[i][j] == 'r')
                    board = setBit(board, (j * 6) + i);
                else if(boardChar[i][j] == 'y')
                    board = clearBit(board,(j * 6) + i);
                else {
                    board = clearBit(board, (j * 6) + i);
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (boardChar[j][i] != 'r' && boardChar[j][i] != 'y'){
                    lastRowIndices[i]=5-j;
                    break;
                }
            }
        }

        int offset = 42;
        for (int i = 0; i < 7; i++) {
            board |= ((long) lastRowIndices[i] << (offset + 3 * i));
        }
        return board;
    }
    public static char[][] longToChar2dArray(long board) {
        char[][] boardChar = new char[6][7];
        int offset = 42;
        for (int j = 0; j < 7; j++) {
            int rowIndex = getValue(board, offset, offset + 2);
            for (int i = rowIndex; i < 6; i++) {
                if (getBit(board, j * 6 + i) == 1)
                    boardChar[i][j] = 'r';
                else
                    boardChar[i][j] = 'y';
            }
            offset += 3;
        }
        return boardChar;
    }

//    public static void main(String[] args) {
//        char[][] charArr = new char[][]{
//                {'0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', 'y', '0', '0', '0', '0'},
//                {'0', 'r', 'r', '0', '0', '0', '0'},
//                {'r', 'y', 'y', 'r', 'y', '0', 'r'}
//            };
//        int[] lastRowIndices = new int[] {5, 4, 3, 5, 5, 6, 5};
//        long board = char2dArrayToLong(charArr, lastRowIndices);
//        String s = Long.toBinaryString(board);
//
//        for (char c : s.toCharArray()) {
//            System.out.print(c + "  ");
//        }
//
//        charArr = longToChar2dArray(board);
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(charArr[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//    }
}
