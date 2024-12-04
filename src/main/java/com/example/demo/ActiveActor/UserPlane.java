package com.example.demo.ActiveActor;

import com.example.demo.Projectiles.UserProjectile;

import java.util.ArrayList;
import java.util.List;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final double MAX_VELOCITY = 8.0; // Max vertical speed
	private static final double ACCELERATION = 1; // Rate of speed change
	private static final double DECELERATION = 1; // Slower stop

	private double targetVelocity; // Desired velocity (set by movement keys)
	private double currentVelocity;
	private int numberOfKills;
	private int scatterShotUses = 5;


	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		setShrinkFactorWidth(0.6);
		setShrinkFactorHeight(0.25);
		this.targetVelocity = 0;
		this.currentVelocity = 0;
	}
	
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
	public int getScatterShotUses() {
		return scatterShotUses;
	}

	public boolean canFireScatterShot() {
		return scatterShotUses > 0;
	}

	public void useScatterShot() {
		if (canFireScatterShot()) {
			scatterShotUses--;
		}
	}
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


	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET),0);
	}


	public void moveUp() {
		targetVelocity = -MAX_VELOCITY;
	}

	public void moveDown() {
		targetVelocity = MAX_VELOCITY;
	}

	public void stop() {
		targetVelocity = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
