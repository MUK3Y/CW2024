package com.example.demo.controller;


import com.example.demo.Levels.LevelParent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";


	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		LevelParent.BGplaySound("/Music/BGM.wav");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu/MainMenu.fxml"));
		Parent root = loader.load();
		Scene mainMenuScene = new Scene(root);
		stage.setScene(mainMenuScene);
		SceneController controller = loader.getController();
		controller.setStage(stage);
		stage.show();


	}

	public static void main(String[] args) {
		launch();
	}
}