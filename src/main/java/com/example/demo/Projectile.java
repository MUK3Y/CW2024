package com.example.demo;

public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		setShrinkFactorWidth(0.25);
		setShrinkFactorHeight(0.25);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}
