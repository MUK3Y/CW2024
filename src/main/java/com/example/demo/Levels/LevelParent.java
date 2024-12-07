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



/**
 * The abstract class `LevelParent` serves as the base class for game levels in a 2D shooter game.
 * It provides core functionalities for handling game mechanics, such as managing actors, user inputs,
 * timeline updates, and rendering the game scene.
 */
public abstract class LevelParent extends Observable {

	// Constants
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150; // Adjustment for enemy positioning
	private static final int MILLISECOND_DELAY = 50; // Game loop delay in milliseconds

	// Fields
	private final double screenHeight; // The height of the game screen
	private final double screenWidth; // The width of the game screen
	private final double enemyMaximumYPosition; // The maximum Y position for enemy actors
	private final Group root; // The root group containing all game elements
	protected final Timeline timeline; // The timeline controlling game updates
	private final UserPlane user; // The player's plane
	private final Scene scene; // The game scene
	private final ImageView background; // The background image of the level
	private boolean isPaused = false; // Tracks if the game is paused
	private Label pauseLabel; // Label for the "Game Paused" message
	protected static Clip backgroundMusicClip; // Clip for background music
	private Label scatterShotLabel; // Label displaying scatter shot usage
	protected static Clip clip; // Clip for sound effects

	private final List<ActiveActorDestructible> friendlyUnits; // List of friendly actors
	private final List<ActiveActorDestructible> enemyUnits; // List of enemy actors
	private final List<ActiveActorDestructible> userProjectiles; // List of projectiles fired by the user
	private final List<ActiveActorDestructible> enemyProjectiles; // List of projectiles fired by enemies
	private int currentNumberOfEnemies; // Current number of active enemies
	private LevelView levelView; // The visual representation of the level
	private boolean isGameActive = true;


	/**
	 * Constructs a `LevelParent` instance with the specified parameters.
	 *
	 * @param backgroundImageName   The name of the background image file
	 * @param screenHeight          The height of the screen
	 * @param screenWidth           The width of the screen
	 * @param playerInitialHealth   The initial health of the player
	 */
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

	// Abstract Methods
	/**
	 * Initializes the friendly units for the level. Must be implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over based on conditions. Must be implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units at defined intervals or conditions. Must be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Creates an instance of `LevelView` for the level. Must be implemented by subclasses.
	 *
	 * @return The `LevelView` instance
	 */
	protected abstract LevelView instantiateLevelView();

	// Initialization Methods
	/**
	 * Initializes the scene by setting up the background and friendly units.
	 *
	 * @return The initialized `Scene` object
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by playing the timeline and focusing on the background.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to the next level by notifying observers with the level name.
	 *
	 * @param levelName The name of the next level
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Updates the game scene, including actor movements, collision checks, and rendering.
	 */
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

	/**
	 * Initializes the timeline for the game loop.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background settings for the game scene, including size, key bindings, and adding to the root.
	 */
	private void initializeBackground() {
		/**
		 * Sets the background to be focusable for capturing user input.
		 */
		background.setFocusTraversable(true);

		/**
		 * Adjusts the background size to match the screen dimensions.
		 */
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		/**
		 * Adds key press event handlers to control user actions.
		 * - UP: Move the player up.
		 * - DOWN: Move the player down.
		 * - SPACE: Fire a projectile.
		 * - X: Fire a scatter shot.
		 * - P: Toggle pause.
		 */
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

		/**
		 * Adds key release event handlers to stop movement when UP or DOWN keys are released.
		 */
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});

		/**
		 * Adds the background to the root group for rendering.
		 */
		root.getChildren().add(background);
	}

	/**
	 * Initializes the pause label with its properties such as font, color, layout, and visibility.
	 */
	protected void initializePauseLabel() {
		pauseLabel = new Label("Game Paused");
		pauseLabel.setFont(new Font("Agency FB", 50));
		pauseLabel.setTextFill(Color.RED);
		pauseLabel.setLayoutX(getScreenWidth() / 2 - 100);
		pauseLabel.setLayoutY(getScreenHeight() / 2 - 50);
		pauseLabel.setVisible(false);

		/**
		 * Adds a glow effect to the pause label for emphasis.
		 */
		Glow glow = new Glow();
		glow.setLevel(0.8);
		pauseLabel.setEffect(glow);

		/**
		 * Adds the pause label to the root group for rendering.
		 */
		getRoot().getChildren().add(pauseLabel);
	}

	/**
	 * Initializes the scatter shot label with the current number of uses remaining, font, color, and position.
	 */
	protected void initializeScatterShotLabel() {
		scatterShotLabel = new Label("Scatter Shots: " + getUser().getScatterShotUses());
		scatterShotLabel.setFont(new Font("Impact", 24));
		scatterShotLabel.setTextFill(Color.WHITE);
		scatterShotLabel.setLayoutX(1111); // Position on screen
		scatterShotLabel.setLayoutY(50);

		/**
		 * Adds the scatter shot label to the root group for rendering.
		 */
		root.getChildren().add(scatterShotLabel);
	}

	/**
	 * Toggles the pause state of the game. Stops or resumes the game timeline and background music.
	 */
	private void togglePauseGame() {
		if (isPaused) {
			/**
			 * Resumes the game if it is currently paused.
			 */
			timeline.play();
			pauseLabel.setVisible(false);
			if (backgroundMusicClip != null && !backgroundMusicClip.isRunning()) {
				backgroundMusicClip.start(); // Resume the music
			}
			isPaused = false;
		} else {
			/**
			 * Pauses the game if it is currently running.
			 */
			timeline.pause();
			pauseLabel.setVisible(true);
			if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
				backgroundMusicClip.stop(); // Pause the music
			}
			isPaused = true;
		}
	}

	/**
	 * Plays a sound effect from the specified file.
	 *
	 * @param soundFile The path to the sound file to be played.
	 */
	public void playSound(String soundFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays looping background music from the specified file.
	 *
	 * @param soundFile The path to the background music file to be played.
	 */
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


	/**
	 * Fires a single projectile from the user, adds it to the game root, and updates the projectiles list.
	 * Plays a sound effect when fired.
	 */
	private void fireProjectile() {
		if (!isGameActive) return;
		if(isPaused) return;
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
		playSound("/Music/pew.wav");
	}

	/**
	 * Fires multiple scatter projectiles from the user, adds them to the game root, and updates the projectiles list.
	 * Plays a sound effect when fired. Stops the firing sound if scatter shot uses are depleted.
	 */
	private void fireScatterShot() {
		if (!isGameActive) return;
		if (isPaused) return;
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

	/**
	 * Generates projectiles fired by enemy units and spawns them in the game.
	 */
	private void generateEnemyFire() {
		if (!isGameActive) return;
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Adds an enemy projectile to the game root and updates the enemy projectiles list.
	 *
	 * @param projectile The enemy projectile to be added.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the state of all active actors, including friendly units, enemy units, and projectiles.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the game root and their respective lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the given list and game root.
	 *
	 * @param actors The list of actors to check and remove if destroyed.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between friendly planes and enemy planes, applying damage when they intersect.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy planes, applying damage when they intersect.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly planes, applying damage when they intersect.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Detects and processes collisions between two lists of actors.
	 * If two actors' bounds intersect, both take damage.
	 *
	 * @param actors1 The first list of actors.
	 * @param actors2 The second list of actors.
	 */
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

	/**
	 * Checks if any enemy unit has penetrated defenses. If so, the user takes damage, and the enemy is destroyed.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}


	/**
	 * Updates the level view by removing hearts corresponding to the user's current health
	 * and updating the scatter shot label to reflect the remaining uses.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		scatterShotLabel.setText("Scatter Shots: " + getUser().getScatterShotUses());
	}

	/**
	 * Updates the user's kill count based on the number of enemies defeated in the level.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated the defenses by verifying if its position exceeds the screen width.
	 *
	 * @param enemy The enemy to check.
	 * @return True if the enemy has passed the screen boundaries, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Stops the game timeline and displays a win image, indicating the user has won the game.
	 */
	protected void winGame() {
		isGameActive = false;
		timeline.stop();
		levelView.showWinImage();

	}

	/**
	 * Stops the game timeline, displays a game over image, stops background music, and plays the lose sound effect,
	 * indicating the user has lost the game.
	 */
	protected void loseGame() {
		isGameActive = false;
		timeline.stop();
		levelView.showGameOverImage();
		backgroundMusicClip.stop();
		playSound("/Music/Lose.wav");
	}

	/**
	 * Gets the user's plane instance.
	 *
	 * @return The {@link UserPlane} object representing the user's plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Gets the root group containing all game elements.
	 *
	 * @return The {@link Group} object representing the game root.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Gets the current number of active enemy units in the game.
	 *
	 * @return The number of enemy units remaining.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the game and displays it in the root group.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Gets the maximum Y position that enemies can occupy.
	 *
	 * @return The maximum Y position for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Gets the screen width of the game.
	 *
	 * @return The screen width in pixels.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Gets the screen height of the game.
	 *
	 * @return The screen height in pixels.
	 */
	protected double getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Checks if the user's plane has been destroyed.
	 *
	 * @return True if the user's plane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of active enemies by setting the count to the current size of the enemy units list.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
