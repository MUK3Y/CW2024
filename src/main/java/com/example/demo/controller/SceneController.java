package com.example.demo.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class SceneController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button quitButton;


    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {

        startGameButton.setOnAction(this::handleStartGame);
        startGameButton.setOnMouseEntered(event -> {
            startGameButton.setTextFill(Color.DARKRED);
        });

        startGameButton.setOnMouseExited(event -> {
            startGameButton.setTextFill(Color.WHITE);
        });

        quitButton.setOnAction(this::handleQuit);
        quitButton.setOnMouseEntered(event -> {
            quitButton.setTextFill(Color.DARKRED);
        });

        quitButton.setOnMouseExited(event -> {
            quitButton.setTextFill(Color.WHITE);
        });

    }

    private void handleStartGame(ActionEvent event) {
        try {

            new Controller(stage).launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleQuit(ActionEvent event) {
        stage.close();
}
}
