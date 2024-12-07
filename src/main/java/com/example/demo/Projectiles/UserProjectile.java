package com.example.demo.Projectiles;

/**
 * Represents a projectile fired by the user in the game.
 * This class extends {@link Projectile} and adds functionality to allow the
 * projectile to move at a specific angle and speed.
 */
public class UserProjectile extends Projectile {

	/** The image file name for the user projectile. */
	private static final String IMAGE_NAME = "userfire.png";

	/** The height of the user projectile image. */
	private static final int IMAGE_HEIGHT = 125;

	/** The speed of the user projectile. */
	private static final int SPEED = 15;

	/** The angle at which the projectile is fired (in degrees). */
	private double angle;

	/**
	 * Constructs a new UserProjectile with the specified initial position and angle.
	 *
	 * @param initialXPos   the initial X-coordinate of the projectile.
	 * @param initialYPos   the initial Y-coordinate of the projectile.
	 * @param angle         the angle at which the projectile is fired, in degrees.
	 */
	public UserProjectile(double initialXPos, double initialYPos, double angle) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.angle = angle;
	}

	/**
	 * Updates the position of the user projectile based on its angle and speed.
	 * The projectile moves horizontally and vertically based on the calculated
	 * velocity components in the X and Y directions.
	 *
	 * The angle is converted from degrees to radians for the trigonometric calculations.
	 */
	@Override
	public void updatePosition() {
		double radians = Math.toRadians(angle);
		moveHorizontally(SPEED * Math.cos(radians)); // Move horizontally based on the angle.
		moveVertically(SPEED * Math.sin(radians));   // Move vertically based on the angle.
	}

	/**
	 * Updates the actor's position by calling the {@link #updatePosition()} method.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
