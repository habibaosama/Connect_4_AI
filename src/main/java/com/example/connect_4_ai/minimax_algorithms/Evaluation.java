package com.example.connect_4_ai.minimax_algorithms;

public class Evaluation {
    public static int ROWS =6;
    public static int COLUMNS =7;
    public static int evaluateScore(char[][] board){
        int score = 0;
        //Horizontal groups check
        for(int i = 0; i < ROWS; i++ )
            for(int j = 0; j < COLUMNS -3 ; j++)
                score += HorizontalWindow(board, i, j);

        //Vertical groups check
        for(int j = 0; j < COLUMNS; j++)
            for(int i = 0; i < ROWS -3 ; i++ )
                score += verticalWindow(board, i, j);

        for(int i = 0; i < ROWS - 3 ; i++ )
            for(int j = 0; j < COLUMNS - 3 ; j++ ){
                score += positiveDiagonalWindow(board, i, j);
                score += negativeDiagonalWindow(board, i, COLUMNS - j - 1);
            }

        //count pieces in middle of the board so chance to win is more
        for(int i = 0; i < ROWS; i++){
            if( board[i][COLUMNS /2] == 'r' )
                score+= 1;
        }

        return score;
    }
    private static int HorizontalWindow(char[][] board, int startRow, int startColumn){
        int cpuPieces = 0,oppPieces = 0, empty = 0;

        for(int i=startColumn; i<startColumn+4; i++){
            if(board[startRow][i]=='r'){
                cpuPieces++;
            }else if(board[startRow][i]=='y'){
                oppPieces++;
            }else{
                empty++;
            }
        }
        return calculatePointsWeight(cpuPieces,oppPieces,empty);
    }


    private static int verticalWindow(char[][] board, int startRow, int startColumn){
        int cpuPieces = 0,oppPieces = 0, empty = 0;
        for(int i = startRow; i < startRow + 4; i++){
            if(board[i][startColumn] == 'r')
                cpuPieces++;
            else if(board[i][startColumn] == 'y')
                oppPieces++;
            else
                empty++;
        }
        return calculatePointsWeight(cpuPieces, oppPieces, empty);
    }

    private static int positiveDiagonalWindow(char[][] board, int startRow, int startColumn ){
        int CPUPieces = 0,oppPieces = 0, empty = 0;

        for(int i = startRow; i < startRow + 4; i++){
            if(board[i][startColumn] == 'r')
                CPUPieces++;
            else if(board[i][startColumn] == 'y')
                oppPieces++;
            else
                empty++;
            //increment column here
            startColumn++;
        }
        return calculatePointsWeight(CPUPieces, oppPieces,empty);
    }

    private static int negativeDiagonalWindow(char[][] board, int startRow, int startColumn){
        int cpuPieces = 0,oppPieces = 0, empty = 0;

        for(int i = startRow; i < startRow + 4; i++){
            if(board[i][startColumn] =='r')
                cpuPieces++;
            else if(board[i][startColumn] == 'y')
                oppPieces++;
            else
                empty++;
            //decrement col
            startColumn --;
        }
        return calculatePointsWeight(cpuPieces, oppPieces, empty);
    }
    private static int calculatePointsWeight(int cpuPieces, int oppPieces, int empty) {
        if(cpuPieces==4){
            return 100;
        }else if(cpuPieces==3 && empty==1){
            return 20;
        }else if(cpuPieces==2 && empty==2){
            return 6;
        }
        if(oppPieces==4){
            return -150;
        }else if(oppPieces==3 && empty==1){
            return -60;
        }else if(oppPieces==2 && empty==2){
            return -6;
        }
        return 0;
    }




    // calculating score of player
    ///////////////////////////////////
   /* public static int eval(char[][] board, char player) {
        int score = 0;
        for (int col = 0; col < 7; col++) {
            int row = 0;
            for (int i = 0; i < 6; i++) {
                if (board[i][col] != '\u0000') {
                    row = i;
                    break;
                }
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    score += checkPoints(board, row, col, i, j, player);
                    score += checkPoints(board, (row - i), (col - j), i, j, player);
                    score += checkPoints(board, (row - (i * 2)), (col - (j * 2)), i, j, player);
                    score += checkPoints(board, (row - (i * 3)), (col - (j * 3)), i, j, player);
                }
            }
        }
       // System.out.println("Score "+score);

        return score;
    }

    public static int checkPoints(char[][] board, int col, int row, int dx, int dy, char player) {
        int i = row, j = col;
        int opPoints = 0;
        int playerPoints = 0;
        int empty = 0;
        int points = 0;
        for (int k = 0; k < 4; k++) {
            if (!checkBounds(i, j))
                return 0;
            if (board[i][j] != player && board[i][j] != '\u0000')
                opPoints++;
            else if (board[i][j] == player)
                playerPoints++;
            else
                empty++;

            i += dx;
            j += dy;
        }

        return points;
        return calculatePoints(opPoints, playerPoints,empty);
    }

    private static boolean checkBounds(int i, int j) {
        if (i < 0 || i >= 6 || j < 0 || j >= 7) return false;
        return true;
    }

    private static int calculatePoints(int opPoints, int playerPoints,int empty) {
         if(playerPoints==4){
            return 100;
        }else if(playerPoints==3 && empty==1){
            return 20;
        }else if(playerPoints==2 && empty==2){
            return 6;
        }
        if(opPoints==4){
            return -150;
        }else if(opPoints==3 && empty==1){
            return -60;
       }else if(opPoints==2 && empty==2){
            return -6;
        }
        return 0;
    }
*/

}
