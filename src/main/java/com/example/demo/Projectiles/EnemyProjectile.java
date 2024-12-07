package com.example.demo.Projectiles;

/**
 * Represents a projectile fired by an enemy character in the game.
 * Extends the {@link Projectile} class to provide specific behavior
 * and appearance for enemy projectiles.
 */
public class EnemyProjectile extends Projectile {

	/** The file name of the image used to represent the enemy's projectile. */
	private static final String IMAGE_NAME = "enemyFire.png";

	/** The height of the enemy projectile image. */
	private static final int IMAGE_HEIGHT = 50;

	/** The horizontal velocity of the enemy projectile, moving from right to left. */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs a new EnemyProjectile instance with the specified initial
	 * X and Y coordinates.
	 *
	 * @param initialXPos the initial X-coordinate of the projectile.
	 * @param initialYPos the initial Y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		setShrinkFactorHeight(0.15); // Set the vertical shrink factor for scaling the image.
		setShrinkFactorWidth(0.35);  // Set the horizontal shrink factor for scaling the image.
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally.
	 * The horizontal velocity is defined by {@link #HORIZONTAL_VELOCITY}.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile.
	 * Currently, this method only updates the projectile's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
