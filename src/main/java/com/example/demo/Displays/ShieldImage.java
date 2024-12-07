/**
 * The {@code ShieldImage} class represents a shield visual element in the game.
 * It is a specialized {@code ImageView} used to display or hide a shield image
 * at a specified position within the game's UI.
 */
package com.example.demo.Displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {

	/** Path to the shield image resource. */
	private static final String IMAGE_NAME = "/images/shield.png";

	/** The size (both height and width) of the shield image in pixels. */
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a {@code ShieldImage} object with the specified position.
	 *
	 * @param xPosition the x-coordinate where the shield image will be placed
	 * @param yPosition the y-coordinate where the shield image will be placed
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
		this.setVisible(false); // Shield is initially hidden
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Makes the shield image visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}
