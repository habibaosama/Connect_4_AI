package com.example.connect_4_ai.minimax_algorithms;

public class Evaluation {
    public static int eval(char[][] board, char player) {
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
       /* System.out.println("player "+ playerPoints);
        System.out.println("op "+ opPoints);
        System.out.println("empty "+ empty);*/
        /*if (playerPoints == 4)
            points += 100;
        else if (playerPoints == 3 && empty == 1)
            points += 5;
        else if (playerPoints == 2 && empty == 2)
            points += 2;

        if (opPoints == 3 && empty == 1)
            points -= 100;

        if (opPoints == 2 && empty == 2)
            points -= 2;

        if(opPoints== 4 )
            points-=200;

        return points;*/
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
            return -1*150;
        }else if(opPoints==3 && empty==1){
            return -60;
       }else if(opPoints==2 && empty==2){
            return -1*6;
        }
        return 0;
    }


}
