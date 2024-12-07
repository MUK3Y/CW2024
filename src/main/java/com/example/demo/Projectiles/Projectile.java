package com.example.demo.Projectiles;

import com.example.demo.ActiveActor.ActiveActorDestructible;

/**
 * An abstract class representing a projectile in the game.
 * Extends {@link ActiveActorDestructible} to provide the behavior of projectiles,
 * including movement and damage handling. Specific types of projectiles, such as
 * those fired by enemies or the player, should extend this class and implement
 * the abstract {@link #updatePosition()} method.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new Projectile with the specified image, dimensions, and initial position.
	 *
	 * @param imageName     the file name of the image representing the projectile.
	 * @param imageHeight   the height of the projectile image.
	 * @param initialXPos   the initial X-coordinate of the projectile.
	 * @param initialYPos   the initial Y-coordinate of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		setShrinkFactorWidth(0.15); // Set the horizontal shrink factor for scaling the image.
		setShrinkFactorHeight(0.15); // Set the vertical shrink factor for scaling the image.
	}

	/**
	 * Handles the damage taken by the projectile. In this case, the projectile is destroyed.
	 * This method overrides the {@link ActiveActorDestructible#takeDamage()} method to destroy
	 * the projectile when it takes damage.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Abstract method that should be implemented by subclasses to update the position of the projectile.
	 * This method is called to update the position of the projectile each frame of the game.
	 */
	@Override
	public abstract void updatePosition();
}
