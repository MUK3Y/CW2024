package com.example.demo.ActiveActor;

import com.example.demo.Destructible;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	private double shrinkFactorWidth = 0.3;
	private double shrinkFactorHeight = 0.3;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos, double shrinkFactorWidth, double shrinkFactorHeight) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.shrinkFactorWidth = shrinkFactorWidth;
		this.shrinkFactorHeight = shrinkFactorHeight;
		isDestroyed = false;
	}

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this(imageName, imageHeight, initialXPos, initialYPos, 0.3, 0.3);
	}


	public void setShrinkFactorWidth(double shrinkFactorWidth) {
		this.shrinkFactorWidth = shrinkFactorWidth;
	}


	public void setShrinkFactorHeight(double shrinkFactorHeight) {
		this.shrinkFactorHeight = shrinkFactorHeight;
	}

	public Bounds getAdjustedBounds(){
		Bounds originalBounds = this.getBoundsInParent();

		return new BoundingBox(
				originalBounds.getMinX() + (originalBounds.getWidth() * (1 - shrinkFactorWidth) /2),
				originalBounds.getMinY() + (originalBounds.getHeight() * (1 - shrinkFactorHeight) / 2),
				originalBounds.getWidth() * shrinkFactorWidth,
				originalBounds.getHeight() * shrinkFactorHeight

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