package com.example.demo.Levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.ActiveActor.ActiveActorDestructible;
import com.example.demo.ActiveActor.FighterPlane;
import com.example.demo.Displays.LevelView;
import com.example.demo.ActiveActor.UserPlane;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	protected final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private boolean isPaused = false;
	private Label pauseLabel;
	protected static Clip backgroundMusicClip;
	private Label scatterShotLabel;
	protected static Clip clip;




	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	
	private int currentNumberOfEnemies;
	private LevelView levelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();

		friendlyUnits.add(user);
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	protected void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
				if (kc == KeyCode.X) fireScatterShot();
				if (kc == KeyCode.P) togglePauseGame();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}



	protected void initializePauseLabel() {
		pauseLabel = new Label("Game Paused");
		pauseLabel.setFont(new Font("Agency FB", 50));
		pauseLabel.setTextFill(Color.RED);
		pauseLabel.setLayoutX(getScreenWidth() / 2 - 100);
		pauseLabel.setLayoutY(getScreenHeight() / 2 - 50);
		pauseLabel.setVisible(false);
		Glow glow = new Glow();
		glow.setLevel(0.8);
		pauseLabel.setEffect(glow);
		getRoot().getChildren().add(pauseLabel);
	}
	protected void initializeScatterShotLabel() {
		scatterShotLabel = new Label("Scatter Shots: " + getUser().getScatterShotUses());
		scatterShotLabel.setFont(new Font("Impact", 24));
		scatterShotLabel.setTextFill(Color.WHITE);
		scatterShotLabel.setLayoutX(1111); // Position on screen
		scatterShotLabel.setLayoutY(50);
		root.getChildren().add(scatterShotLabel);
	}


	private void togglePauseGame() {
		if (isPaused) {
			timeline.play();
			pauseLabel.setVisible(false);
			isPaused = false;
			if (backgroundMusicClip != null && !backgroundMusicClip.isRunning()) {
				backgroundMusicClip.start(); // Resume the music
			}
			isPaused = false;
		} else {
			timeline.pause();
			pauseLabel.setVisible(true);
			isPaused = true;
			if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
				backgroundMusicClip.stop(); // Pause the music
			}
			isPaused = true;
		}
	}


		public void playSound(String soundFile) {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
				clip  = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public static void BGplaySound(String soundFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(LevelParent.class.getResource(soundFile));
			backgroundMusicClip = AudioSystem.getClip();
			backgroundMusicClip.open(audioInputStream);
			backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music
			backgroundMusicClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
		playSound("/Music/pew.wav");
	}
	private void fireScatterShot() {

		List<ActiveActorDestructible> projectiles = user.fireScatterShot();

		for (ActiveActorDestructible projectile : projectiles) {
			root.getChildren().add(projectile);
			userProjectiles.add(projectile);
		}
			playSound("/Music/pew.wav");
		if (user.getScatterShotUses() == 0) {
			clip.stop();
		}
	}


	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> {
			enemy.updateActor();
		});
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed()).collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}


	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getAdjustedBounds().intersects(otherActor.getAdjustedBounds())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		scatterShotLabel.setText("Scatter Shots: " + getUser().getScatterShotUses());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();

	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		backgroundMusicClip.stop();
		playSound("/Music/Lose.wav");

	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}
	protected double getScreenHeight(){
		return screenHeight;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
