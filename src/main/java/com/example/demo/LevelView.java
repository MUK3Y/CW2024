package com.example.demo;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -60;
	private static final int LOSS_SCREEN_Y_POSITION = -300;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	private double screenWidth;
	private double screenHeight;

	private ImageView backgroundImageView;
	private ImageView backgroundImageView2;


	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.screenWidth = 1300;
		this.screenHeight = 750;

		Image backgroundImage = new Image(getClass().getResource("/com/example/demo/images/background1.jpg").toExternalForm());
		this.backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitHeight(screenHeight);
		backgroundImageView.setFitWidth(screenWidth);

		Image backgroundImage2 = new Image(getClass().getResource("/com/example/demo/images/background2.jpg").toExternalForm());
		this.backgroundImageView2 = new ImageView(backgroundImage2);
		backgroundImageView2.setFitHeight(screenHeight);
		backgroundImageView2.setFitWidth(screenWidth);

	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(backgroundImageView2);
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {

		root.getChildren().add(backgroundImageView);

		root.getChildren().add(gameOverImage);
		gameOverImage.setScaleX(0.65);
		gameOverImage.setScaleY(0.65);

	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
