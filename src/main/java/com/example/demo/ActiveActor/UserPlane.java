/**
 * The {@code UserPlane} class represents the player's fighter plane in the game.
 * It extends the {@code FighterPlane} class and provides functionality for movement,
 * firing single projectiles, and a scatter-shot feature.
 */
package com.example.demo.ActiveActor;

import com.example.demo.Projectiles.UserProjectile;
import java.util.ArrayList;
import java.util.List;

public class UserPlane extends FighterPlane {

	/** The file name of the user plane's image. */
	private static final String IMAGE_NAME = "userplane.png";

	/** The upper and lower bounds for the plane's Y-axis movement. */
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;

	/** The initial position and dimensions of the plane. */
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;

	/** Projectile firing offsets and velocity constants. */
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final double MAX_VELOCITY = 8.0; // Max vertical speed
	private static final double ACCELERATION = 1.0; // Rate of speed change
	private static final double DECELERATION = 1.0; // Slower stop

	/** Movement and firing state variables. */
	private double targetVelocity; // Desired velocity (set by movement keys)
	private double currentVelocity;
	private int numberOfKills;
	private int scatterShotUses = 5;

	/**
	 * Constructs a {@code UserPlane} with the specified initial health.
	 *
	 * @param initialHealth the starting health of the user's plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		setShrinkFactorWidth(0.6);
		setShrinkFactorHeight(0.25);
		this.targetVelocity = 0;
		this.currentVelocity = 0;
	}

	/**
	 * Updates the position of the user's plane based on the current velocity and target velocity.
	 * Ensures the plane stays within the defined Y-axis bounds.
	 */
	@Override
	public void updatePosition() {
		if (currentVelocity < targetVelocity) {
			currentVelocity = Math.min(currentVelocity + ACCELERATION, targetVelocity);
		} else if (currentVelocity > targetVelocity) {
			currentVelocity = Math.max(currentVelocity - DECELERATION, targetVelocity);
		}

		double initialTranslateY = getTranslateY();
		this.moveVertically(currentVelocity);
		double newPosition = getLayoutY() + getTranslateY();
		if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
			this.setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the state of the user's plane, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a single projectile directly in front of the user's plane.
	 *
	 * @return a {@code UserProjectile} fired from the plane
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET), 0);
	}

	/**
	 * Fires a scatter shot of three projectiles with different trajectories if available.
	 *
	 * @return a list of {@code UserProjectile} objects representing the scatter shot
	 */
	public List<ActiveActorDestructible> fireScatterShot() {
		if (!canFireScatterShot()) {
			return new ArrayList<>();
		}

		useScatterShot();
		List<ActiveActorDestructible> projectiles = new ArrayList<>();

		double xPos = PROJECTILE_X_POSITION;
		double yPos = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);

		projectiles.add(new UserProjectile(xPos, yPos, 0));
		projectiles.add(new UserProjectile(xPos, yPos, -5));
		projectiles.add(new UserProjectile(xPos, yPos, 5));
		return projectiles;
	}

	/**
	 * Sets the target velocity to move the plane upward.
	 */
	public void moveUp() {
		targetVelocity = -MAX_VELOCITY;
	}

	/**
	 * Sets the target velocity to move the plane downward.
	 */
	public void moveDown() {
		targetVelocity = MAX_VELOCITY;
	}

	/**
	 * Stops the vertical movement of the user's plane by setting the target velocity to zero.
	 */
	public void stop() {
		targetVelocity = 0;
	}

	/**
	 * Gets the number of scatter shot uses remaining.
	 *
	 * @return the number of scatter shot uses left
	 */
	public int getScatterShotUses() {
		return scatterShotUses;
	}

	/**
	 * Checks if the user can fire a scatter shot.
	 *
	 * @return {@code true} if scatter shot uses are greater than zero, {@code false} otherwise
	 */
	public boolean canFireScatterShot() {
		return scatterShotUses > 0;
	}

	/**
	 * Decrements the number of scatter shot uses by one.
	 */
	public void useScatterShot() {
		if (canFireScatterShot()) {
			scatterShotUses--;
		}
	}

	/**
	 * Gets the total number of kills by the user's plane.
	 *
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}
