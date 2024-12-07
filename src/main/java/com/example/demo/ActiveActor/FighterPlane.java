/**
 * The {@code FighterPlane} class is an abstract representation of a fighter plane in the game.
 * It extends {@code ActiveActorDestructible} and provides additional functionality for handling health
 * and firing projectiles. Subclasses are expected to implement specific behaviors such as projectile firing.
 */
package com.example.demo.ActiveActor;

public abstract class FighterPlane extends ActiveActorDestructible {

	/** The current health of the fighter plane. */
	private int health;

	/**
	 * Constructs a {@code FighterPlane} object with the specified properties.
	 *
	 * @param imageName    the name of the image file representing the fighter plane
	 * @param imageHeight  the height of the fighter plane image
	 * @param initialXPos  the initial X position of the fighter plane
	 * @param initialYPos  the initial Y position of the fighter plane
	 * @param health       the initial health of the fighter plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method to be implemented by subclasses to define how the fighter plane fires projectiles.
	 *
	 * @return a new {@code ActiveActorDestructible} representing the fired projectile
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the fighter plane's health by 1. If health reaches 0, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for a projectile based on the fighter plane's position and a horizontal offset.
	 *
	 * @param xPositionOffset the horizontal offset for the projectile's initial position
	 * @return the calculated X position for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for a projectile based on the fighter plane's position and a vertical offset.
	 *
	 * @param yPositionOffset the vertical offset for the projectile's initial position
	 * @return the calculated Y position for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the fighter plane's health has reached zero.
	 *
	 * @return {@code true} if health is zero, otherwise {@code false}
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Returns the current health of the fighter plane.
	 *
	 * @return the health of the fighter plane
	 */
	public int getHealth() {
		return health;
	}
}
