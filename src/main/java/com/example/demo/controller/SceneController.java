/**
 * The {@code SceneController} class manages the initial game menu scene, providing
 * functionality to start the game or quit the application.
 */
package com.example.demo.controller;


import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceneController {

    /** Button for starting the game. */
    @FXML
    private Button startGameButton;

    /** Button for quitting the application. */
    @FXML
    private Button quitButton;

    /** The primary JavaFX stage for the application. */
    private Stage stage;

    /**
     * Sets the {@code Stage} used by the controller.
     *
     * @param stage the primary {@code Stage} for the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the scene controller and sets up event handlers for UI elements.
     * This method is automatically called after the FXML is loaded.
     */
    @FXML
    public void initialize() {

        // Set the action for the "Start Game" button
        startGameButton.setOnAction(this::handleStartGame);

        // Change text color when the mouse hovers over the "Start Game" button
        startGameButton.setOnMouseEntered(event -> {
            startGameButton.setTextFill(Color.DARKRED);
        });

        // Restore text color when the mouse exits the "Start Game" button
        startGameButton.setOnMouseExited(event -> {
            startGameButton.setTextFill(Color.WHITE);
        });

        // Set the action for the "Quit" button
        quitButton.setOnAction(this::handleQuit);

        // Change text color when the mouse hovers over the "Quit" button
        quitButton.setOnMouseEntered(event -> {
            quitButton.setTextFill(Color.DARKRED);
        });

        // Restore text color when the mouse exits the "Quit" button
        quitButton.setOnMouseExited(event -> {
            quitButton.setTextFill(Color.WHITE);
        });
    }

    /**
     * Handles the "Start Game" button action. Initializes the game by invoking the {@code Controller}.
     *
     * @param event the {@code ActionEvent} triggered by clicking the "Start Game" button
     */
    private void handleStartGame(ActionEvent event) {
        try {
            // Create and launch the game through the Controller
            new Controller(stage).launchGame();
        } catch (Exception e) {
            // Print stack trace for debugging in case of an exception
            e.printStackTrace();
        }
    }

    /**
     * Handles the "Quit" button action. Closes the application.
     *
     * @param event the {@code ActionEvent} triggered by clicking the "Quit" button
     */
    private void handleQuit(ActionEvent event) {
        stage.close();
    }
}
