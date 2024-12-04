package com.example.demo.Projectiles;

import com.example.demo.ActiveActor.ActiveActorDestructible;

public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		setShrinkFactorWidth(0.15);
		setShrinkFactorHeight(0.15);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}
