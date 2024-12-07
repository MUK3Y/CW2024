/**
 * The {@code ActiveActor} class is an abstract base class for creating
 * actors in a graphical application. It extends the {@code ImageView} class
 * to provide image-based visual representation and functionality for updating
 * positions on the screen.
 */
package com.example.demo.ActiveActor;

import javafx.scene.image.*;

public abstract class ActiveActor extends ImageView {

	/**
	 * Location of the image resources for the actor.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an {@code ActiveActor} object with the specified image name, image height,
	 * and initial position.
	 *
	 * @param imageName   the name of the image file (relative to {@code IMAGE_LOCATION})
	 * @param imageHeight the height of the image to be displayed
	 * @param initialXPos the initial X-coordinate for the actor
	 * @param initialYPos the initial Y-coordinate for the actor
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method to be implemented by subclasses to define
	 * how the actor's position is updated.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove the distance to move the actor along the X-axis
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove the distance to move the actor along the Y-axis
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
