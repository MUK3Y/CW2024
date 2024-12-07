/**
 * The {@code Boss} class represents a boss enemy in the game, extending the {@code FighterPlane} class.
 * It implements advanced behaviors such as firing projectiles, activating shields, and moving in a predefined
 * but randomized pattern. The boss has a health pool, shield capabilities, and unique movement and attack logic.
 */
package com.example.demo.ActiveActor;

import com.example.demo.Projectiles.BossProjectile;
import java.util.*;

public class Boss extends FighterPlane {

	/** Name of the image representing the boss. */
	private static final String IMAGE_NAME = "bossplane.png";

	/** Initial X position of the boss. */
	private static final double INITIAL_X_POSITION = 1000.0;

	/** Initial Y position of the boss. */
	private static final double INITIAL_Y_POSITION = 400;

	/** Offset for the Y position of projectiles fired by the boss. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/** Probability of the boss firing in a single frame. */
	private static final double BOSS_FIRE_RATE = 0.04;

	/** Probability of the boss activating its shield in a single frame. */
	private static final double BOSS_SHIELD_PROBABILITY = 0.002;

	/** Height of the boss image. */
	private static final int IMAGE_HEIGHT = 300;

	/** Vertical movement velocity for the boss. */
	private static final int VERTICAL_VELOCITY = 8;

	/** Initial health of the boss. */
	public static int HEALTH = 50;

	/** Number of moves in a movement cycle. */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/** Constants for movement and shielding. */
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	/** List representing the boss's movement pattern. */
	private final List<Integer> movePattern;

	/** Whether the boss is shielded. */
	private boolean isShielded;

	/** Number of consecutive moves in the same direction. */
	private int consecutiveMovesInSameDirection;

	/** Index of the current move in the move pattern. */
	private int indexOfCurrentMove;

	/** Number of frames the shield has been activated. */
	private int framesWithShieldActivated;

	/**
	 * Constructs a new {@code Boss} object with its default position, health, and movement pattern.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the boss's position based on its movement pattern, ensuring it stays within boundaries.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's state, including its position and shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the boss decides to shoot in the current frame.
	 *
	 * @return a new {@code BossProjectile} if fired, or {@code null} otherwise
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Reduces the boss's health if it is not shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the boss's movement pattern with vertical movements and pauses.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the boss's shield status, activating or deactivating it based on conditions.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}
		if (shieldExhausted()) {
			deactivateShield();
		}
	}

	/**
	 * Retrieves the next move in the movement pattern.
	 *
	 * @return the next movement step
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines whether the boss should fire in the current frame.
	 *
	 * @return {@code true} if the boss fires, {@code false} otherwise
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial Y position for a projectile fired by the boss.
	 *
	 * @return the initial Y position for the projectile
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines whether the boss should activate its shield in the current frame.
	 *
	 * @return {@code true} if the shield should be activated, {@code false} otherwise
	 */
	public boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks whether the shield duration has been exhausted.
	 *
	 * @return {@code true} if the shield duration is exhausted, {@code false} otherwise
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield, shrinking its collision bounds.
	 */
	public void activateShield() {
		isShielded = true;
		setShrinkFactorHeight(0.4);
		setShrinkFactorWidth(0.7);
	}

	/**
	 * Deactivates the boss's shield and resets its collision bounds.
	 */
	public void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		setShrinkFactorHeight(0.1);
		setShrinkFactorWidth(0.5);
	}

	/**
	 * Retrieves the number of frames the shield has been activated.
	 *
	 * @return the number of shield activation frames
	 */
	public int getFramesWithShieldActivated() {
		return framesWithShieldActivated;
	}

	/**
	 * Checks whether the boss is currently shielded.
	 *
	 * @return {@code true} if shielded, {@code false} otherwise
	 */
	public boolean isShielded() {
		return isShielded;
	}

	/**
	 * Sets the boss's shield status and updates its shield state accordingly.
	 *
	 * @param shielded {@code true} to shield the boss, {@code false} to unshield
	 */
	public void setShielded(boolean shielded) {
		isShielded = shielded;
		if (!isShielded) {
			deactivateShield();
		}
	}
}
