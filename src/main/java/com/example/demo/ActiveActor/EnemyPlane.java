/**
 * The {@code EnemyPlane} class represents an enemy fighter plane in the game.
 * It extends the {@code FighterPlane} class, inheriting basic functionality,
 * and adds specific behavior such as movement and firing projectiles.
 */
package com.example.demo.ActiveActor;

import com.example.demo.Projectiles.EnemyProjectile;

public class EnemyPlane extends FighterPlane {

	/** The name of the image file used to represent the enemy plane. */
	private static final String IMAGE_NAME = "enemyplane.png";

	/** The height of the enemy plane image. */
	private static final int IMAGE_HEIGHT = 150;

	/** The horizontal velocity of the enemy plane. */
	private static final int HORIZONTAL_VELOCITY = -6;

	/** The horizontal offset for the initial position of the projectile. */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/** The vertical offset for the initial position of the projectile. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/** The initial health of the enemy plane. */
	private static final int INITIAL_HEALTH = 1;

	/** The probability of the enemy plane firing a projectile per frame. */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an {@code EnemyPlane} object with a specified initial position.
	 *
	 * @param initialXPos the initial X position of the enemy plane
	 * @param initialYPos the initial Y position of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		setShrinkFactorHeight(0.25);
		setShrinkFactorWidth(0.6);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile if the enemy plane decides to shoot in the current frame.
	 *
	 * @return a new {@code EnemyProjectile} if fired, or {@code null} otherwise
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
