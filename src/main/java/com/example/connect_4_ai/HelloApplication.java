package com.example.connect_4_ai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        loader.load();
        HelloController controller = loader.getController();
        Group group = new Group();
        ImageView imageView = new ImageView("file:src/main/resources/images/connect-4-1.jpg");
        Button singleBtn = new Button("Single Player");
        Button multiBtn = new Button("Multi Player");
        singleBtn.setLayoutX(210);
        singleBtn.setLayoutY(230);
        multiBtn.setLayoutX(210);
        multiBtn.setLayoutY(280);
        multiBtn.setPrefWidth(133);
        singleBtn.setOnAction(e -> {
            try {
                controller.onePlayerGame();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        multiBtn.setOnAction(e -> {
            try {
                controller.twoPlayersGame();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        group.getChildren().addAll(imageView, singleBtn, multiBtn);
        group.setStyle("-fx-font: 20px \"Impact\";");
        controller.setStage(stage);
        Scene scene = new Scene(group, 552, 580);
        stage.setScene(scene);
        String icon = "file:src/main/resources/images/connect 4 Icon.jpg";
        stage.getIcons().add(new Image(icon));
        stage.setTitle("Connect 4");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}