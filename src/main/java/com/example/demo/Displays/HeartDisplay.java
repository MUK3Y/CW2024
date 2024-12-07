/**
 * The {@code HeartDisplay} class manages the display of heart icons, representing the player's remaining health.
 * It provides methods to initialize and update the heart display during the game.
 */
package com.example.demo.Displays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HeartDisplay {

	/** Path to the heart icon image resource. */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/** The height of each heart icon in pixels. */
	private static final int HEART_HEIGHT = 50;

	/** Index of the first heart icon in the container. */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/** Container for holding the heart icons. */
	private HBox container;

	/** X-coordinate position of the heart display container. */
	private double containerXPosition;

	/** Y-coordinate position of the heart display container. */
	private double containerYPosition;

	/** The initial number of heart icons to display. */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a new {@code HeartDisplay} object with the specified position and number of hearts.
	 *
	 * @param xPosition       the x-coordinate for the heart display container
	 * @param yPosition       the y-coordinate for the heart display container
	 * @param heartsToDisplay the initial number of heart icons to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the {@link HBox} container to hold the heart icons.
	 * Sets the container's position based on the specified x and y coordinates.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart icons and adds them to the container.
	 * Each heart icon is created with the specified size and maintains its aspect ratio.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart icon from the container, typically called when the player loses health.
	 * If the container is empty, no action is taken.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Retrieves the {@link HBox} container holding the heart icons.
	 *
	 * @return the container with the heart icons
	 */
	public HBox getContainer() {
		return container;
	}
}
