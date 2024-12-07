/**
 * The {@code ActiveActorDestructible} class extends {@code ActiveActor} and
 * implements the {@code Destructible} interface, representing an active actor
 * in the game that can be destroyed. It also provides functionality for
 * adjusting its collision bounds.
 */
package com.example.demo.ActiveActor;

import com.example.demo.Destructible;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** Indicates whether the actor is destroyed. */
	private boolean isDestroyed;

	/** Shrink factor for adjusting the width of the collision bounds. */
	private double shrinkFactorWidth = 0.3;

	/** Shrink factor for adjusting the height of the collision bounds. */
	private double shrinkFactorHeight = 0.3;

	/**
	 * Constructs an {@code ActiveActorDestructible} object with specified image details,
	 * position, and shrink factors for collision bounds.
	 *
	 * @param imageName        the name of the image file
	 * @param imageHeight      the height of the image
	 * @param initialXPos      the initial X-coordinate
	 * @param initialYPos      the initial Y-coordinate
	 * @param shrinkFactorWidth the shrink factor for the width of the collision bounds
	 * @param shrinkFactorHeight the shrink factor for the height of the collision bounds
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos,
								   double shrinkFactorWidth, double shrinkFactorHeight) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.shrinkFactorWidth = shrinkFactorWidth;
		this.shrinkFactorHeight = shrinkFactorHeight;
		isDestroyed = false;
	}

	/**
	 * Constructs an {@code ActiveActorDestructible} object with default shrink factors.
	 *
	 * @param imageName   the name of the image file
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X-coordinate
	 * @param initialYPos the initial Y-coordinate
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this(imageName, imageHeight, initialXPos, initialYPos, 0.3, 0.3);
	}

	/**
	 * Sets the shrink factor for the width of the collision bounds.
	 *
	 * @param shrinkFactorWidth the new shrink factor for the width
	 */
	public void setShrinkFactorWidth(double shrinkFactorWidth) {
		this.shrinkFactorWidth = shrinkFactorWidth;
	}

	/**
	 * Sets the shrink factor for the height of the collision bounds.
	 *
	 * @param shrinkFactorHeight the new shrink factor for the height
	 */
	public void setShrinkFactorHeight(double shrinkFactorHeight) {
		this.shrinkFactorHeight = shrinkFactorHeight;
	}

	/**
	 * Returns the adjusted collision bounds of the actor, based on shrink factors.
	 *
	 * @return the adjusted {@code Bounds} object representing the collision area
	 */
	public Bounds getAdjustedBounds() {
		Bounds originalBounds = this.getBoundsInParent();
		return new BoundingBox(
				originalBounds.getMinX() + (originalBounds.getWidth() * (1 - shrinkFactorWidth) / 2),
				originalBounds.getMinY() + (originalBounds.getHeight() * (1 - shrinkFactorHeight) / 2),
				originalBounds.getWidth() * shrinkFactorWidth,
				originalBounds.getHeight() * shrinkFactorHeight
		);
	}

	/**
	 * Abstract method to update the position of the actor.
	 * Must be implemented by subclasses.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Abstract method to update the actor's state.
	 * Must be implemented by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Abstract method to handle damage taken by the actor.
	 * Must be implemented by subclasses.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed by setting the {@code isDestroyed} flag to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed status of the actor.
	 *
	 * @param isDestroyed the new destroyed status
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks whether the actor is destroyed.
	 *
	 * @return {@code true} if the actor is destroyed, {@code false} otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
