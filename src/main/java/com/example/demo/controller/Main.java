/**
 * The {@code Main} class serves as the entry point for the "Sky Battle" game application.
 * It initializes the main menu and sets up the primary stage for the game.
 */
package com.example.demo.controller;

import com.example.demo.Levels.LevelParent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	/** The width of the game window. */
	private static final int SCREEN_WIDTH = 1300;

	/** The height of the game window. */
	private static final int SCREEN_HEIGHT = 750;

	/** The title of the game window. */
	private static final String TITLE = "Sky Battle";

	/**
	 * Starts the JavaFX application by setting up the main menu and the primary stage.
	 *
	 * @param stage the primary {@code Stage} for this JavaFX application
	 * @throws Exception if an error occurs during loading resources or setting up the scene
	 */
	@Override
	public void start(Stage stage) throws Exception {

		// Set the title and dimensions for the main stage
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		// Play background music for the game
		LevelParent.BGplaySound("/Music/BGM.wav");

		// Load the FXML file for the main menu
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu/MainMenu.fxml"));
		Parent root = loader.load();

		// Set up the main menu scene
		Scene mainMenuScene = new Scene(root);
		stage.setScene(mainMenuScene);

		// Configure the scene controller with the primary stage
		SceneController controller = loader.getController();
		controller.setStage(stage);

		// Display the stage
		stage.show();
	}

	/**
	 * The main method, serving as the application's entry point.
	 * Launches the JavaFX application.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		launch();
	}
}
