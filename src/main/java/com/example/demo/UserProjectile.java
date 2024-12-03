package com.example.demo;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int SPEED = 15;
	private double angle;

	public UserProjectile(double initialXPos, double initialYPos, double angle) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.angle = angle;
	}

	@Override
	public void updatePosition() {
		double radians = Math.toRadians(angle);
		moveHorizontally(SPEED * Math.cos(radians));
		moveVertically(SPEED * Math.sin(radians));

	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
