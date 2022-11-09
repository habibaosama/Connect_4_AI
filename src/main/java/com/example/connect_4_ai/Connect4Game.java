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
import java.util.Arrays;


public class Connect4Game {
    private Label playerTurnLabel;
    private boolean playerTurn = true;

    private final Alert alert;
    private ImageView winIcon;
    private ImageView loseIcon;

    private final Image boardImage;
    private final Image redCircleImage;
    private final Image yellowCircleImage;
    private char[][] board;
    private final int[] lastRowIndices;

    public Connect4Game() throws FileNotFoundException {
        String boardPNG = String.format("file:src/main/resources/images/board.png");
        String redCirclePNG = String.format("file:src/main/resources/images/red-circle.png");
        String yellowCirclePNG = String.format("file:src/main/resources/images/yellow-circle.png");
        boardImage = new Image(boardPNG);
        redCircleImage = new Image(redCirclePNG);
        yellowCircleImage = new Image(yellowCirclePNG);
        this.board = new char[6][7];
        lastRowIndices = new int[7];
        Arrays.fill(lastRowIndices, 6);
        alert = new Alert(Alert.AlertType.INFORMATION);
        setIcons();
    }

    public void startGame(Stage stage) throws FileNotFoundException {
        System.out.println("Starting Game");
        board = new char[6][7];
        Arrays.fill(lastRowIndices, 6);
        drawBoard(stage);
    }

    private void drawBoard(Stage stage){
        playerTurnLabel = new Label("Player 1 Turn");
        playerTurnLabel.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        playerTurnLabel.setLayoutX(230); playerTurnLabel.setLayoutY(555);
        Button restartButton = new Button("Restart");
        restartButton.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        restartButton.setLayoutX(0);  restartButton.setLayoutY(552);
        restartButton.setOnAction(e -> {
            try {
                startGame(stage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        Canvas canvas = new Canvas(552,552);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->  {
            System.out.println(e.getX() + " " + e.getY());
            int colIndex = getColIndex(e.getX());
            System.out.println("Index of column : " + colIndex);
            if (isValidColumn(colIndex)) {
                applyChoice(colIndex);
                draw(canvas.getGraphicsContext2D());
                if (win(colIndex)) {
                    alert.setGraphic(winIcon);
                    alert.setTitle("WIN");
                    alert.setHeaderText("Congratulations :)");
                    alert.show();
                }
                switchTurns();
            }

        });
        canvas.getGraphicsContext2D().drawImage(boardImage,0,0,552,552);
        Group root = new Group();
        root.getChildren().addAll(canvas, playerTurnLabel, restartButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void draw(GraphicsContext context){
        for (int i = 5; i >= 0; i--) {
            for (int j = 6; j >= 0; j--) {
                char c = board[i][j];
                if (c == 'r' || c == 'y')
                    context.drawImage(getImage(c),22 + (j * 73), 38 + (81.2 * i), 70, 70);
            }
        }
        printBoard();
    }

    private void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private Image getImage(char c) {
        return switch (c) {
            case 'r' -> redCircleImage;
            case 'y' -> yellowCircleImage;
            default -> null;
        };
    }

    private int getColIndex(double x){
        if(x <= 16 || x >= 536)
            return -1;
        return (int) (x - 16) / 75;
    }

    private boolean isValidColumn(int col) {
        return col != -1 && board[0][col] != '0';
    }

    private void applyChoice(int col) {
        int row = --lastRowIndices[col];
        board[row][col] = getChar();
    }

    private void switchTurns() {
        playerTurn = !playerTurn;
        if (playerTurn)
            playerTurnLabel.setText("Player 1 Turn");
        else
            playerTurnLabel.setText("Player 2 Turn");
    }

    private char getChar() {
        if (playerTurn)
            return 'r';
        else
            return 'y';
    }

    private boolean win(int col) {
        char color = getChar();
        int row = lastRowIndices[col];
        int count = 0;
        for (int i = row + 1; i <= row + 3 && i < 6; i++) {
            if (board[i][col] == color)
                break;
            count++;
        }
        if (count == 3)
            return true;
        count = 0;
        for (int j = col + 1; j <= col + 3 && j < 7; j++) {
            if (board[j][col] != color)
                break;
            count++;
        }
        if (count == 3)
            return true;
        count = 0;
        for (int i = 1; i <= 3; i++) {
            if (row + i < 6 && col + i < 7) {
                if (board[row + i][col + i] != color)
                    break;
                count++;
            }
        }
        if (count == 3)
            return true;
        count = 0;
        for (int i = 1; i <= 3; i++) {
            if (row + i < 6 && col - i >= 0) {
                if (board[row + i][col - i] != color)
                    break;
                count++;
            }
        }
        return count == 3;
    }

    private boolean isFull() {
        for (int index : lastRowIndices) {
            if (index > 0)
                return false;
        }
        return true;
    }

    private void setIcons() {
        String icon1 = String.format("file:src/main/resources/images/icons8-win-48.png");
        String icon2 = String.format("file:src/main/resources/images/icons8-loser-48.png");
        winIcon = new ImageView(icon1);
        loseIcon = new ImageView(icon2);
    }

}
