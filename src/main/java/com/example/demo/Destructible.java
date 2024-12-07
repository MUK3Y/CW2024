package com.example.demo;

/**
 * Interface representing an object that can be damaged and destroyed.
 * Any class implementing this interface should provide implementations
 * for taking damage and being destroyed.
 */
public interface Destructible {

	/**
	 * Takes damage. This method is called when the object is hit or damaged
	 * by another actor or event in the game.
	 */
	void takeDamage();

	/**
	 * Destroys the object. This method is called when the object has taken
	 * enough damage or meets the criteria to be destroyed.
	 */
	void destroy();
}

