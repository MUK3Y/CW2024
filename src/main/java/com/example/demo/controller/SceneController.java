package com.example.demo.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class SceneController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button quitButton;

    // Reference to the main stage
    private Stage stage;

    // Set the stage reference
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Event handlers for buttons
        startGameButton.setOnAction(this::handleStartGame);
        quitButton.setOnAction(this::handleQuit);
    }

    private void handleStartGame(ActionEvent event) {
        try {
            // Start the game by calling the Controller's launchGame method
            new Controller(stage).launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleQuit(ActionEvent event) {
        // Exit the application
        System.out.println("Exiting the game...");
        stage.close();
}
}