package com.example.demo.Levels;

import com.example.demo.ActiveActor.Boss;
import com.example.demo.Displays.LevelView;
import com.example.demo.Displays.LevelViewLevelTwo;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


/**
 * Represents Level 3 of the game, where the player encounters a boss enemy.
 * Extends the LevelParent class and implements specific functionality
 * for this level, such as boss mechanics and level-specific UI elements.
 */
public class LevelThree extends LevelParent {

    /** The file path to the background image for Level 3. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BG3.jpg";

    /** The initial health value for the player at the start of the level. */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /** The boss enemy for Level 3. */
    private final Boss boss;

    /** The path to the next level after Level 3. */
    private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelBoss";

    /** The view associated with Level 3, including UI elements. */
    private LevelViewLevelTwo levelView;

    /** A UI text element to display the boss's current health. */
    private Text bossHealthCounter;

    /**
     * Constructs a new instance of LevelThree.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth the width of the game screen.
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
        Boss.HEALTH = 100; // Initialize the boss's health
        boss.setShielded(false); // Disable the boss's shield initially
        bossHealthCounter = new Text();
        bossHealthCounter.setFont(new Font("Impact", 24));
        bossHealthCounter.setStyle("-fx-fill: white;");
        bossHealthCounter.setLayoutX(1130); // Position the health counter on the screen
        bossHealthCounter.setLayoutY(40);
        updatebossHealthCounter();
    }

    /**
     * Initializes the friendly units for this level, including the player and UI elements.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
        getRoot().getChildren().add(bossHealthCounter);
        initializePauseLabel();
        initializeScatterShotLabel();
        showLevelThree(" LEVEL 3");
    }

    /**
     * Displays the level name on the screen with a fade-out effect.
     *
     * @param levelName the name of the level to display.
     */
    private void showLevelThree(String levelName) {
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
     * Checks if the game is over due to player destruction or boss defeat.
     * If the player is destroyed, the game is lost. If the boss is destroyed,
     * the game transitions to the next level.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
            timeline.stop();
        } else if (boss.isDestroyed()) {
            winGame();
            goToNextLevel(NEXT_LEVEL);
            timeline.stop();
        }
    }

    /**
     * Spawns enemy units for this level. If no enemies exist, the boss is added.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Instantiates and returns the LevelView for Level 3, which includes custom UI elements.
     *
     * @return a LevelView object representing the view for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    /**
     * Updates the text of the boss health counter to reflect the current health of the boss.
     */
    private void updatebossHealthCounter() {
        bossHealthCounter.setText("Boss Health: " + boss.getHealth());
    }

    /**
     * Updates the game scene during each frame of the game loop.
     * Includes updating the boss health counter and unshielding the boss.
     */
    @Override
    protected void updateScene() {
        super.updateScene();
        updatebossHealthCounter();
        boss.setShielded(false); // Ensure the boss is not shielded
    }
}
