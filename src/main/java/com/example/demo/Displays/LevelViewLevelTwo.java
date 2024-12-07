/**
 * The {@code LevelViewLevelTwo} class is a specialized view for the second level of the game.
 * It extends {@link LevelView} and adds a shield image to the level display, along with methods to show or hide the shield.
 */
package com.example.demo.Displays;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	/** The x-coordinate position of the shield image. */
	private static final int SHIELD_X_POSITION = 1150;

	/** The y-coordinate position of the shield image. */
	private static final int SHIELD_Y_POSITION = 500;

	/** The root group of the scene graph for this level view. */
	private final Group root;

	/** The shield image displayed in this level view. */
	private final ShieldImage shieldImage;

	/**
	 * Constructs a {@code LevelViewLevelTwo} object, initializing the level view with the given root node and hearts display.
	 *
	 * @param root           the root group node for the level's scene graph
	 * @param heartsToDisplay the number of hearts to display in the heart display
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image to the root group.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

}
