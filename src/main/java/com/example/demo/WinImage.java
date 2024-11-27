package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/winBanner.png";
	private static final int HEIGHT = 600;
	private static final int WIDTH = 600;
	
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition-20);
		this.setLayoutY(yPosition-150);
	}
	
	public void showWinImage() {
		this.setVisible(true);
	}

}
