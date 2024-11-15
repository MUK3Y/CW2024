package com.example.demo;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	public Bounds getAdjustedBounds(){
		Bounds originalBounds = this.getBoundsInParent();
		double shrinkFactor = 0.3;

		return new BoundingBox(
				originalBounds.getMinX() + (originalBounds.getWidth() * (1 - shrinkFactor) /2),
				originalBounds.getMinY() + (originalBounds.getHeight() * (1 - shrinkFactor) / 2),
				originalBounds.getWidth() * shrinkFactor,
				originalBounds.getHeight() * shrinkFactor

		);
	}

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy() {
		setDestroyed(true);
	}

	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

}
