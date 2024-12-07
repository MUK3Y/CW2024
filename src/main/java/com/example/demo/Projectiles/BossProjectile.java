package com.example.demo.Projectiles;

/**
 * Represents a projectile fired by the boss character in the game.
 * Extends the {@link Projectile} class and provides specific behavior
 * and appearance for the boss's projectiles.
 */
public class BossProjectile extends Projectile {

	/** The file name of the image used to represent the boss's projectile. */
	private static final String IMAGE_NAME = "fireball.png";

	/** The height of the projectile image. */
	private static final int IMAGE_HEIGHT = 75;

	/** The horizontal velocity of the projectile, moving from right to left. */
	private static final int HORIZONTAL_VELOCITY = -15;

	/** The initial X-coordinate for the projectile when created. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a new BossProjectile instance with the specified initial Y-coordinate.
	 *
	 * @param initialYPos the initial Y-coordinate of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
		setShrinkFactorWidth(0.7); // Set the horizontal shrink factor for scaling the image.
		setShrinkFactorHeight(0.4); // Set the vertical shrink factor for scaling the image.
	}

	/**
	 * Updates the projectile's position by moving it horizontally.
	 * The horizontal velocity is defined by {@link #HORIZONTAL_VELOCITY}.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile.
	 * Currently, this method only updates the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
