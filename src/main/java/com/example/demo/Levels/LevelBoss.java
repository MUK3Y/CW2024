package com.example.demo.Levels;

import com.example.demo.ActiveActor.Boss;
import com.example.demo.Displays.LevelView;
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

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BossBG.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	private ShieldImage shieldImage;
	private Text bossHealthCounter;

	public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
		shieldImage = new ShieldImage(boss.getLayoutX(), boss.getLayoutY());

		bossHealthCounter = new Text();
		bossHealthCounter.setFont(new Font("Impact",24));
		bossHealthCounter.setStyle("-fx-fill: white;");
		bossHealthCounter.setLayoutX(1120);
		bossHealthCounter.setLayoutY(40);
		updatebossHealthCounter();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(bossHealthCounter);
		initializePauseLabel();
		initializeScatterShotLabel();
		showLevelBoss("FINAL BOSS");

	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();

			backgroundMusicClip.stop();
			playSound("/Music/Win.wav");
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getRoot().getChildren().add(shieldImage);
			shieldImage.showShield();
		}
	}
	private void showLevelBoss(String levelName) {

		Label levelLabel = new Label(levelName);
		levelLabel.setFont(new Font("Times New ROman", 100));
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
		fadeTransition.setOnFinished(e -> getRoot().getChildren().remove(levelLabel)); // Remove label after fading

		fadeTransition.play();
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

	private void updatebossHealthCounter(){
		bossHealthCounter.setText("Boss Health: "+ boss.getHealth());
	}

	private void updateShieldPosition(){
		if (shieldImage != null && boss != null){
			double offsetX = -65;
			double offsetY = 50;
			shieldImage.setTranslateX(boss.getTranslateX() + offsetX);
			shieldImage.setTranslateY(boss.getTranslateY() + offsetY);
		}
	}

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
