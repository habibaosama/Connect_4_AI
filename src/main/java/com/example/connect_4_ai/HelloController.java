package com.example.connect_4_ai;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class HelloController {
    private Stage stage;
    @FXML
    protected void onePlayerGame() throws FileNotFoundException {
        Connect4Game connect4Game = new Connect4Game(true);
        connect4Game.startGame(stage);
    }

    @FXML
    protected void twoPlayersGame() throws FileNotFoundException {
        Connect4Game connect4Game = new Connect4Game(false);
        connect4Game.startGame(stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}