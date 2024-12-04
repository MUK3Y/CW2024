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
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BG1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private Text killCountText;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			timeline.stop();
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
			timeline.stop();
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		initializeKillCountText();
		initializePauseLabel();
		initializeScatterShotLabel();
		showLevelOne("LEVEL 1");
	}

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

	private void initializeKillCountText(){
		killCountText = new Text();
		killCountText.setFont(new Font("Impact",24));
		killCountText.setStyle("-fx-fill: White");
		killCountText.setLayoutX(1200);
		killCountText.setLayoutY(40);
		getRoot().getChildren().add(killCountText);
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
	private void showLevelOne(String levelName) {

		Label levelLabel = new Label(levelName);
		levelLabel.setFont(new Font("Times New ROman", 100));
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

	@Override
	protected void updateScene() {
		super.updateScene();
		updateKillCountText();
	}

	private void updateKillCountText() {
		killCountText.setText("Kills: " + getUser().getNumberOfKills());
	}

}
