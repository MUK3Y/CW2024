/**
 * The {@code LevelView} class provides a visual representation of the game level's UI components,
 * including the heart display, win image, game over image, and background images.
 * It manages the display and state of these elements during the gameplay.
 */
package com.example.demo.Displays;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class LevelView {

	/** X-coordinate for the heart display. */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/** Y-coordinate for the heart display. */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/** X-coordinate for the win image. */
	private static final int WIN_IMAGE_X_POSITION = 355;

	/** Y-coordinate for the win image. */
	private static final int WIN_IMAGE_Y_POSITION = 175;

	/** X-coordinate for the game over image. */
	private static final int LOSS_SCREEN_X_POSITION = -60;

	/** Y-coordinate for the game over image. */
	private static final int LOSS_SCREEN_Y_POSITION = -300;

	/** The root group of the scene graph for this level view. */
	private final Group root;

	/** The image displayed when the player wins. */
	private final WinImage winImage;

	/** The image displayed when the player loses. */
	private final GameOverImage gameOverImage;

	/** The heart display to show the player's remaining health. */
	private final HeartDisplay heartDisplay;

	/** Width of the screen. */
	private double screenWidth;

	/** Height of the screen. */
	private double screenHeight;

	/** Image view for the game over background. */
	private ImageView backgroundImageView;

	/** Image view for the win background. */
	private ImageView backgroundImageView2;

	/**
	 * Constructs a {@code LevelView} object and initializes all the visual components.
	 *
	 * @param root             the root group node for the level's scene graph
	 * @param heartsToDisplay  the number of hearts to display in the heart display
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.screenWidth = 1300;
		this.screenHeight = 750;

		// Initialize the background images
		Image backgroundImage = new Image(getClass().getResource("/com/example/demo/images/loseBG.jpg").toExternalForm());
		this.backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitHeight(screenHeight);
		backgroundImageView.setFitWidth(screenWidth);

		Image backgroundImage2 = new Image(getClass().getResource("/com/example/demo/images/WinBG.jpg").toExternalForm());
		this.backgroundImageView2 = new ImageView(backgroundImage2);
		backgroundImageView2.setFitHeight(screenHeight);
		backgroundImageView2.setFitWidth(screenWidth);
	}

	/**
	 * Adds the heart display to the root group to make it visible.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win screen by adding the win background and win image to the root group.
	 */
	public void showWinImage() {
		root.getChildren().add(backgroundImageView2);
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over screen by adding the lose background and game over image to the root group.
	 * Scales the game over image for proper display.
	 */
	public void showGameOverImage() {
		root.getChildren().add(backgroundImageView);
		root.getChildren().add(gameOverImage);
		gameOverImage.setScaleX(0.65);
		gameOverImage.setScaleY(0.65);
	}

	/**
	 * Updates the heart display to reflect the remaining hearts.
	 *
	 * @param heartsRemaining the number of hearts remaining to display
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
