/**
 * The {@code WinImage} class represents a visual element that displays a "win" banner in the game.
 * It extends {@code ImageView} and provides functionality to show the image dynamically at a
 * specified position on the screen.
 */
package com.example.demo.Displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {

	/** Path to the "win" banner image resource. */
	private static final String IMAGE_NAME = "/com/example/demo/images/winBanner.png";

	/** The height of the "win" banner image in pixels. */
	private static final int HEIGHT = 600;

	/** The width of the "win" banner image in pixels. */
	private static final int WIDTH = 600;

	/**
	 * Constructs a {@code WinImage} object with the specified position.
	 *
	 * @param xPosition the x-coordinate for the banner's position
	 * @param yPosition the y-coordinate for the banner's position
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false); // The image is initially hidden
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition - 20); // Adjusted for better placement
		this.setLayoutY(yPosition - 150); // Adjusted for better placement
	}

	/**
	 * Makes the "win" banner image visible on the screen.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
