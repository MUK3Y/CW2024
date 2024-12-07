package com.example.demo.Levels;

import com.example.demo.ActiveActor.ActiveActorDestructible;
import com.example.demo.ActiveActor.EnemyPlane;
import com.example.demo.Displays.LevelView;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Represents Level 2 of the game, where the player faces multiple enemy planes.
 * Extends the LevelParent class and implements specific functionality
 * for this level, such as tracking the kill count and spawning enemy units.
 */
public class LevelTwo extends LevelParent {

    /** The file path to the background image for Level 2. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BG2.jpg";

    /** The path to the next level after Level 2. */
    private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelThree";

    /** The total number of enemies to spawn at a time. */
    private static final int TOTAL_ENEMIES = 5;

    /** The number of kills required to advance to the next level. */
    private static final int KILLS_TO_ADVANCE = 20;

    /** The probability of spawning a new enemy. */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

    /** The initial health value for the player at the start of the level. */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /** A UI text element to display the player's current kill count. */
    private Text killCountText;

    /**
     * Constructs a new instance of LevelTwo.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth the width of the game screen.
     */
    public LevelTwo(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the game is over.
     * If the player is destroyed, the game ends with a loss.
     * If the player reaches the required kill target, the game advances to the next level.
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
     * Initializes the friendly units for this level, including the player and UI elements.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
        initializeKillCountText();
        initializePauseLabel();
        initializeScatterShotLabel();
        showLevelTwo("LEVEL 2");
    }

    /**
     * Displays the level name on the screen with a fade-out effect.
     *
     * @param levelName the name of the level to display.
     */
    private void showLevelTwo(String levelName) {
        Label levelLabel = new Label(levelName);
        levelLabel.setFont(new Font("Times New Roman", 100));
        levelLabel.setTextFill(Color.WHITE);
        levelLabel.setLayoutX(getScreenWidth() / 2 - 200);
        levelLabel.setLayoutY(getScreenHeight() / 2 - 100);

        Glow glow = new Glow();
        glow.setLevel(0.8); // Apply glow effect to the label
        levelLabel.setEffect(glow);

        getRoot().getChildren().add(levelLabel);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), levelLabel);
        fadeTransition.setFromValue(1.0); // Start fully visible
        fadeTransition.setToValue(0.0); // Fade to invisible
        fadeTransition.setOnFinished(e -> getRoot().getChildren().remove(levelLabel)); // Remove label after fading

        fadeTransition.play();
    }

    /**
     * Spawns enemy units for this level. Spawns up to TOTAL_ENEMIES
     * based on the defined ENEMY_SPAWN_PROBABILITY.
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
     * Initializes the kill count text display on the screen.
     */
    private void initializeKillCountText() {
        killCountText = new Text();
        killCountText.setFont(new Font("Impact", 24));
        killCountText.setStyle("-fx-fill: White");
        killCountText.setLayoutX(1200);
        killCountText.setLayoutY(40);
        getRoot().getChildren().add(killCountText);
    }

    /**
     * Instantiates and returns the LevelView for Level 2.
     *
     * @return a LevelView object representing the view for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the player has reached the required kill target to advance.
     *
     * @return true if the player has reached the kill target, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    /**
     * Updates the game scene during each frame of the game loop.
     * Includes updating the kill count text.
     */
    @Override
    protected void updateScene() {
        super.updateScene();
        updateKillCountText();
    }

    /**
     * Updates the text of the kill count display to reflect the player's current kill count.
     */
    private void updateKillCountText() {
        killCountText.setText("Kills: " + getUser().getNumberOfKills());
    }
}
