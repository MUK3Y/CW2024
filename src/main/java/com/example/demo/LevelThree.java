package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/BG3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private static final String NEXT_LEVEL = "com.example.demo.LevelBoss";
    private LevelViewLevelTwo levelView;
    private Text bossHealthCounter;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
        Boss.HEALTH = 100;
        boss.setShielded(false);
        bossHealthCounter = new Text();
        bossHealthCounter.setFont(new Font("Impact",24));
        bossHealthCounter.setStyle("-fx-fill: white;");
        bossHealthCounter.setLayoutX(1130);
        bossHealthCounter.setLayoutY(40);
        updatebossHealthCounter();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
        getRoot().getChildren().add(bossHealthCounter);
        initializePauseLabel();
        initializeScatterShotLabel();
        showLevelThree(" LEVEL 3");

    }
    private void showLevelThree(String levelName) {

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
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
            timeline.stop();
        }
        else if (boss.isDestroyed()) {
            winGame();
            goToNextLevel(NEXT_LEVEL);
            timeline.stop();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);


        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    private void updatebossHealthCounter(){
        bossHealthCounter.setText("Boss Health: "+ boss.getHealth());
    }


    @Override
    protected void updateScene() {
        super.updateScene();
        updatebossHealthCounter();
        boss.setShielded(false);


    }
}
