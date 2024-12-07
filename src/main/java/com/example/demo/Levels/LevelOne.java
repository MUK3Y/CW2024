/**
 * The {@code LevelOne} class represents the first level of the game.
 * It extends {@code LevelParent} and handles the logic, display, and gameplay for the initial level.
 */
package com.example.demo.Levels;

import com.example.demo.ActiveActor.ActiveActorDestructible;
import com.example.demo.ActiveActor.EnemyPlane;
import com.example.demo.Displays.LevelView;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.effect.Glow;

public class LevelOne extends LevelParent {

	/** Path to the background image for Level One. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BG1.jpg";

	/** Fully qualified name of the next level's class. */
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelTwo";

	/** Total number of enemy planes present at any given time. */
	private static final int TOTAL_ENEMIES = 5;

	/** Number of kills required to advance to the next level. */
	private static final int KILLS_TO_ADVANCE = 10;

	/** Probability of spawning an enemy plane during each spawn cycle. */
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

	/** Initial health of the player. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** Text object to display the player's current kill count. */
	private Text killCountText;

	/**
	 * Constructs a {@code LevelOne} object with the specified screen dimensions.
	 *
	 * @param screenHeight the height of the game screen
	 * @param screenWidth the width of the game screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over due to either the player's destruction or the completion of the kill target.
	 * Ends the game or advances to the next level accordingly.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			timeline.stop();
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
			timeline.stop();
		}
	}

	/**
	 * Initializes the friendly units and UI components for Level One.
	 * Adds the player, kill count display, and level label to the scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		initializeKillCountText();
		initializePauseLabel();
		initializeScatterShotLabel();
		showLevelOne("LEVEL 1");
	}

	/**
	 * Spawns enemy planes if the total number of active enemies is less than the allowed maximum.
	 * Each spawn is governed by a probability factor.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Initializes the kill count display for the level.
	 */
	private void initializeKillCountText() {
		killCountText = new Text();
		killCountText.setFont(new Font("Impact", 24));
		killCountText.setStyle("-fx-fill: White;");
		killCountText.setLayoutX(1200);
		killCountText.setLayoutY(40);
		getRoot().getChildren().add(killCountText);
	}

	/**
	 * Creates and returns a {@code LevelView} object to manage UI elements specific to Level One.
	 *
	 * @return a new {@code LevelView} instance
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Determines whether the player has achieved the kill target to advance to the next level.
	 *
	 * @return {@code true} if the player's kills are greater than or equal to the target; {@code false} otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Displays a label for Level One with glowing and fading effects.
	 *
	 * @param levelName the name of the level to display
	 */
	private void showLevelOne(String levelName) {
		Label levelLabel = new Label(levelName);
		levelLabel.setFont(new Font("Times New Roman", 100));
		levelLabel.setTextFill(Color.WHITE);
		levelLabel.setLayoutX(getScreenWidth() / 2 - 200);
		levelLabel.setLayoutY(getScreenHeight() / 2 - 100);

		Glow glow = new Glow();
		glow.setLevel(0.8);
		levelLabel.setEffect(glow);

		getRoot().getChildren().add(levelLabel);

		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), levelLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setOnFinished(e -> getRoot().getChildren().remove(levelLabel)); // Remove label after fading

		fadeTransition.play();
	}

	/**
	 * Updates the game scene during each frame of the game loop.
	 * Updates the kill count display.
	 */
	@Override
	protected void updateScene() {
		super.updateScene();
		updateKillCountText();
	}

	/**
	 * Updates the kill count display text based on the player's current kill count.
	 */
	private void updateKillCountText() {
		killCountText.setText("Kills: " + getUser().getNumberOfKills());
	}
}
