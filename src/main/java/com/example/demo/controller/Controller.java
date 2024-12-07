/**
 * The {@code Controller} class manages the game's main logic, including level transitions
 * and interactions with the game stages. It observes the game levels for updates and handles
 * transitions between different levels dynamically.
 */
package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;

public class Controller implements Observer {

	/** The fully qualified name of the first level's class. */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";

	/** The JavaFX stage used to display the game. */
	private final Stage stage;

	/**
	 * Constructs a {@code Controller} instance.
	 *
	 * @param stage the primary JavaFX {@code Stage} where the game is displayed
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by initializing the stage and transitioning to the first level.
	 *
	 * @throws ClassNotFoundException       if the level class cannot be found
	 * @throws NoSuchMethodException        if the level constructor cannot be found
	 * @throws SecurityException            if access to the level constructor is restricted
	 * @throws InstantiationException       if the level class cannot be instantiated
	 * @throws IllegalAccessException       if access to the level class or constructor is illegal
	 * @throws IllegalArgumentException     if an argument provided to the constructor is invalid
	 * @throws InvocationTargetException    if the constructor invocation throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to a specified game level by dynamically loading and instantiating
	 * the level's class.
	 *
	 * @param className the fully qualified name of the level class
	 * @throws ClassNotFoundException       if the level class cannot be found
	 * @throws NoSuchMethodException        if the level constructor cannot be found
	 * @throws SecurityException            if access to the level constructor is restricted
	 * @throws InstantiationException       if the level class cannot be instantiated
	 * @throws IllegalAccessException       if access to the level class or constructor is illegal
	 * @throws IllegalArgumentException     if an argument provided to the constructor is invalid
	 * @throws InvocationTargetException    if the constructor invocation throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// Load the specified class dynamically
		Class<?> myClass = Class.forName(className);

		// Get the constructor with two double parameters
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);

		// Instantiate the level using the constructor
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

		// Add this controller as an observer of the level
		myLevel.addObserver(this);

		// Initialize the scene for the level and set it on the stage
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);

		// Start the game logic for the level
		myLevel.startGame();
	}

	/**
	 * Updates the controller when the observed object changes. Typically used to transition
	 * to a new level when notified by the current level.
	 *
	 * @param observable the observable object
	 * @param arg        an argument passed to the {@code notifyObservers} method, usually the next level's class name
	 */
	@Override
	public void update(Observable observable, Object arg) {
		try {
			goToLevel((String) arg);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			// Show an error alert if transitioning to the next level fails
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}
