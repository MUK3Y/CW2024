/**
 * The {@code LevelBoss} class represents the final level of the game featuring a boss fight.
 * It extends {@code LevelParent} and manages the logic, display, and interactions specific to the boss level.
 */
package com.example.demo.Levels;

import com.example.demo.ActiveActor.Boss;
import com.example.demo.Displays.LevelViewLevelTwo;
import com.example.demo.Displays.ShieldImage;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.effect.Glow;

public class LevelBoss extends LevelParent {

	/** Path to the background image for the boss level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BossBG.jpg";

	/** Initial health for the player. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The boss character in the level. */
	private final Boss boss;

	/** The specific view for this level, containing UI elements and effects. */
	private LevelViewLevelTwo levelView;

	/** The shield image displayed around the boss when shielded. */
	private ShieldImage shieldImage;

	/** Displays the boss's current health. */
	private final Text bossHealthCounter;

	/**
	 * Constructs a {@code LevelBoss} object with the specified screen dimensions.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth the width of the game screen
	 */
	public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
		shieldImage = new ShieldImage(boss.getLayoutX(), boss.getLayoutY());
		bossHealthCounter = new Text();
		bossHealthCounter.setFont(new Font("Impact", 24));
		bossHealthCounter.setStyle("-fx-fill: white;");
		bossHealthCounter.setLayoutX(1120);
		bossHealthCounter.setLayoutY(40);
		updatebossHealthCounter();
	}

	/**
	 * Initializes the friendly units and UI elements for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(bossHealthCounter);
		initializePauseLabel();
		initializeScatterShotLabel();
		showLevelBoss("FINAL BOSS");
	}

	/**
	 * Checks if the game is over by evaluating the player and boss states.
	 * Ends the game with a win or loss scenario accordingly.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			winGame();
			backgroundMusicClip.stop();
			playSound("/Music/Win.wav");
		}
	}

	/**
	 * Spawns enemy units. Adds the boss and its shield to the game when enemies are depleted.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getRoot().getChildren().add(shieldImage);
			shieldImage.showShield();
		}
	}

	/**
	 * Displays the level name with a glowing effect and fade-out animation.
	 *
	 * @param levelName the name of the level to display
	 */
	private void showLevelBoss(String levelName) {
		Label levelLabel = new Label(levelName);
		levelLabel.setFont(new Font("Times New Roman", 100));
		levelLabel.setTextFill(Color.DARKGOLDENROD);
		levelLabel.setLayoutX(getScreenWidth() / 2 - 300);
		levelLabel.setLayoutY(getScreenHeight() / 2 - 100);

		Glow glow = new Glow();
		glow.setLevel(0.8);
		levelLabel.setEffect(glow);

		getRoot().getChildren().add(levelLabel);

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), levelLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setOnFinished(e -> getRoot().getChildren().remove(levelLabel));
		fadeTransition.play();
	}

	/**
	 * Creates the {@code LevelView} specific to this level.
	 *
	 * @return the {@code LevelView} instance
	 */
	@Override
	protected LevelViewLevelTwo instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

	/**
	 * Updates the text of the boss health counter.
	 */
	private void updatebossHealthCounter() {
		bossHealthCounter.setText("Boss Health: " + boss.getHealth());
	}

	/**
	 * Updates the position of the shield image to follow the boss.
	 */
	private void updateShieldPosition() {
		if (shieldImage != null && boss != null) {
			double offsetX = -65;
			double offsetY = 50;
			shieldImage.setTranslateX(boss.getTranslateX() + offsetX);
			shieldImage.setTranslateY(boss.getTranslateY() + offsetY);
		}
	}

	/**
	 * Updates the scene during each frame of the game loop, handling shield state and UI updates.
	 */
	@Override
	protected void updateScene() {
		super.updateScene();
		updateShieldPosition();

		if (boss.getFramesWithShieldActivated() == 0) {
			getRoot().getChildren().remove(shieldImage);
			shieldImage.hideShield();
			boss.deactivateShield();
		}

		if (!boss.isShielded() && boss.shieldShouldBeActivated() && boss.getFramesWithShieldActivated() == 0) {
			boss.activateShield();
			getRoot().getChildren().add(shieldImage);
			shieldImage.showShield();
		}

		updatebossHealthCounter();
	}
}
