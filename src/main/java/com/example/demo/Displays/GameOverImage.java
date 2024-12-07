/**
 * The {@code GameOverImage} class represents the "Game Over" image displayed on the screen
 * when the game ends. It extends {@link ImageView} and is used to manage the display of
 * the game-over graphic.
 */
package com.example.demo.Displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {

	/** Path to the "Game Over" image resource. */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a new {@code GameOverImage} object with the specified position on the screen.
	 * The image is loaded from the provided resource path.
	 *
	 * @param xPosition the x-coordinate where the image will be positioned
	 * @param yPosition the y-coordinate where the image will be positioned
	 */
	public GameOverImage(double xPosition, double yPosition) {
		// Load and set the "Game Over" image
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		// Set the position of the image on the screen
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
