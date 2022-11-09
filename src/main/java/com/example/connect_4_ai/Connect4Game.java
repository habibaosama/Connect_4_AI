package com.example.connect_4_ai;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


public class Connect4Game {
    // player 1 red || player 2 yellow
    private int score1, score2;
    private Label playerTurnLabel;
    private boolean player1Turn = true;
    private boolean singlePlayer;
    private final Alert alert;
    private ImageView winIcon;
    private ImageView loseIcon;
    private final Image boardImage;
    private final Image redCircleImage;
    private final Image yellowCircleImage;
    private char[][] board;
    private int[] lastRowIndices;

    public Connect4Game(boolean singlePlayer) throws FileNotFoundException {
            this.singlePlayer = singlePlayer;
            boardImage = new Image(new FileInputStream("src\\main\\resources\\images\\board.png"));
            redCircleImage = new Image(new FileInputStream("src\\main\\resources\\images\\red-circle.png"));
            yellowCircleImage = new Image(new FileInputStream("src\\main\\resources\\images\\yellow-circle.png"));
            this.board = new char[6][7];
            lastRowIndices = new int[7];
            alert = new Alert(Alert.AlertType.INFORMATION);
            winIcon = new ImageView(new Image(new FileInputStream("src/main/resources/images/icons8-win-48.png")));
            loseIcon = new ImageView(new Image(new FileInputStream("src/main/resources/images/icons8-loser-48.png")));
        }

        public void startGame (Stage stage) throws FileNotFoundException {
            System.out.println("Starting Game");
            board = new char[6][7];
            Arrays.fill(lastRowIndices, 5);
            drawBoard(stage);
        }

        private void drawBoard (Stage stage){
            playerTurnLabel = new Label("Player 1 Turn");
            playerTurnLabel.setFont(Font.font("Impact", FontWeight.BOLD, 20));
            playerTurnLabel.setLayoutX(230);
            playerTurnLabel.setLayoutY(555);
            Button restartButton = new Button("Restart");
            restartButton.setFont(Font.font("Impact", FontWeight.BOLD, 20));
            restartButton.setLayoutX(0);
            restartButton.setLayoutY(552);
            restartButton.setOnAction(e -> {
                try {
                    startGame(stage);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            Button backButton = new Button("Back");
            backButton.setFont(Font.font("Impact", FontWeight.BOLD, 20));
            backButton.setLayoutX(87);
            backButton.setLayoutY(552);
            backButton.setOnAction(e -> {
                try {
                    new HelloApplication().start(stage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            Canvas canvas = new Canvas(552, 552);
            canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println(e.getX() + " " + e.getY());
                int colIndex = getColIndex(e.getX());
                System.out.println("Index of column : " + colIndex);
                if (isValidColumn(colIndex)) {
                    applyChoice(colIndex);
                    draw(canvas.getGraphicsContext2D());
                    if (win2(colIndex)) {
                        alert.setGraphic(winIcon);
                        alert.setTitle("WIN");
                        alert.setHeaderText("Congratulations :)");
                        alert.show();
                    }
                    switchTurns();
                }

            });
            canvas.getGraphicsContext2D().drawImage(boardImage, 0, 0, 552, 552);
            Group root = new Group();
            root.getChildren().addAll(canvas, playerTurnLabel, restartButton, backButton);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

        public void draw (GraphicsContext context){
            for (int i = 5; i >= 0; i--) {
                for (int j = 6; j >= 0; j--) {
                    char c = board[i][j];
                    if (c == 'r' || c == 'y')
                        context.drawImage(getImage(c), 22 + (j * 73), 38 + (81.2 * i), 70, 70);
                }
            }
            printBoard();
        }

        private void printBoard () {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        private Image getImage ( char c){
            return switch (c) {
                case 'r' -> redCircleImage;
                case 'y' -> yellowCircleImage;
                default -> null;
            };
        }

        private int getColIndex ( double x){
            if (x <= 16 || x >= 536)
                return -1;
            return (int) (x - 16) / 75;
        }

        private boolean isValidColumn ( int col){
            return col > -1 && col < 7 && lastRowIndices[col] > -1;
        }

        private void applyChoice ( int col){
            int row = lastRowIndices[col]--;
            board[row][col] = getChar();
        }

        private void switchTurns () {
            player1Turn = !player1Turn;
            if (player1Turn)
                playerTurnLabel.setText("Player 1 Turn");
            else
                playerTurnLabel.setText("Player 2 Turn");
        }

        private char getChar () {
            if (player1Turn)
                return 'r';
            else
                return 'y';
        }

        private boolean win2 ( int col){
            int row = lastRowIndices[col] + 1;
            char color = getChar();
            int leftCount = 0, rightCount = 0;
            // vertical check
            for (int i = row + 1; i < row + 4; i++) {
                if (i < 6 && board[i][col] == color)
                    rightCount++;
                else
                    break;
            }

            for (int i = row - 1; i > row - 4; i--) {
                if (i > -1 && board[i][col] == color)
                    leftCount++;
                else
                    break;
            }
            if (leftCount + rightCount + 1 > 3)
                return true;

            // horizontal check
            leftCount = 0;
            rightCount = 0;
            for (int j = col + 1; j < col + 4; j++) {
                if (j < 7 && board[row][j] == color)
                    rightCount++;
                else
                    break;
            }

            for (int j = col - 1; j > col - 4; j--) {
                if (j > -1 && board[row][j] == color)
                    leftCount++;
                else
                    break;
            }
            if (leftCount + rightCount + 1 > 3)
                return true;

            // right diagonal check
            leftCount = 0;
            rightCount = 0;
            for (int k = 1; k < 4; k++) {
                if (row - k > -1 && col + k < 7 && board[row - k][col + k] == color)
                    rightCount++;
                else
                    break;
            }

            for (int k = 1; k < 4; k++) {
                if (row + k < 6 && col - k > -1 && board[row + k][col - k] == color)
                    leftCount++;
                else
                    break;
            }
            if (leftCount + rightCount + 1 > 3)
                return true;

            // left diagonal check
            leftCount = 0;
            rightCount = 0;

            for (int k = 1; k < 4; k++) {
                if (row + k < 6 && col + k < 7 && board[row + k][col + k] == color)
                    rightCount++;
                else
                    break;
            }

            for (int k = 1; k < 4; k++) {
                if (row - k > -1 && col - k > -1 && board[row - k][col - k] == color)
                    leftCount++;
                else
                    break;
            }
            if (leftCount + rightCount + 1 > 3)
                return true;
            return false;
        }

        private boolean isFull () {
            for (int index : lastRowIndices) {
                if (index > -1)
                    return false;
            }
            return true;
        }
}
