1. Github: https://github.com/MUK3Y/CW2024

**2. Compilation Instructions**

    2.1. Ensure the computer running the program has its volume set loud enough for the user to hear the game's audio clearly.

    2.2. Download the zip file from GitHub and extract the MakLeJoshua_Intellij_23.zip file.

    2.3. Launch IntelliJ and load only the CW2024-master folder into the IDE.

    2.4. When prompted at the bottom-right corner, load Maven.

    2.5. Navigate to and open the Main file located at src/main/java/com.example.demo/Controller/Main.java.

    2.6. Once Maven has finished loading and Main.java is open in the editor, run the main method to start the game.

**3. Implemented and working properly**
  
    3.1. Added Main Menu **Innovative feature**
      3.1.1. Created Main Menu with Play button to start the game and Quit button to quit the game. This gives the player time to start the game 
              instead of the game starting right away
            
    3.2. Added Inertia to UserPlane **Innovative feature**
      3.2.1. The plane movement of up and down was very rigid looking and choppy, so added inertia to the plane by adding friction and 
              controlling the speed which made the plane move smoothly.
            
    3.3. Kill counter and its text was added for level one and level two
      3.3.1. The Kill count text is added to the top right corner of the screen, as every plane dies, the kill count increases
              till the necessary amount of kills needed to go to the next level has been reached.

    3.4. Added new level as level two instead of the Boss
      3.4.1. The new level is similar to level one except the kill count needed to move to next level has been doubled.

    3.5. Added another level before boss but after level two, as level three
      3.5.1 This level is similar to boss level but the the Boss health is half and it does not have the shield feature.

    3.6. Added Boss Health counter and text to level three and level boss(level four)
      3.6.1. As the boss plane gets hit by every bullet, health decreases, the text is added to the top right corner of the screen.

    3.7. Added Background music to the game
      3.7.1. Added a background music that loops continously throughout the game until either when lost or when won.

    3.8. Added Pause feature to the game
      3.8.1. When pressed the key "P" the game will pause which stops the game and music, when pressed again the game continues 
      3.8.2. Added a Pause label. When the key is pressed, "Game Paused" will pop up at the centre of the screen.

    3.9. Changed and added background image
      3.9.1. Changed background image for the original levels in the game, and added new ones to the new levels
      3.9.2. Added backgrounds to "Game Over" and "You Win" images

    3.10. Changed the "You Win" image
      3.10.1 Changed the image to a more suitable one for the win background image
  
    3.11. Added sound for shooting projectiles **Innovative feature**
      3.11.1. Added a "pew" sound for shooting, when every projectile is shot, the sound comes, so when shot multiple projectile, sounds like shooting.

    3.12. Added a scatter shot feature **Innovative feature**
      3.12.1. When the key "X" entered, 3 bullets at 3 different angle will be shot.
      3.12.2. The Shot has "Pew" sound just like the other projectiles
      3.12.3. The scatter shot is limited to only 5 times 

    3.13. Added a scattershot counter and label 
      3.13.1. Added a counter that reduces when every scatter shot is used. Addded a label to the top right corner at every level, 
              right below kill count or boss health respective to their levels.

    3.14. Added level indicator label **Innovative feature**
      3.14.1. Added a label indicating which level the player is playing. The label appears at the centre of the screen and vanishes in 3 seconds.
      3.14.2. The "Final Boss" level has a darkgoldenrod colour to notify it is the last level in the game.

**4. Implemented but not Working Properly**

    4.1. Scatter shot sound issue
      4.1.1. The scatter shout sound works however the scatter shot sound for the last set from the limited 5 sets, the sound goes mute. 
              Due to conditioning a limit on the times of uses of scatter shot.

    4.2. Bullets affecting planes outside screen.
      4.2.1. Bullets hit enemy planes that aren't fully in the screen.

    4.3. Game lag
      4.3.1. The game lags at the end due to maxing out on the file size

  **5. Features not Implemented**

    5.1. Restart feature
      5.1.1. Realised the flexibility this feature offers later than needed, by that time I had already created javadocs and done JUnit tests. 
              Could've done it still however, it requred intertwining multiple classes. Hence didn't have time to implement it. Also due to file
              size space maximised.

  **6. New Java Classes**

    6.1. Class: Level Two
    
      6.1.1. Location: com.example.demo.Levels
      
      6.1.2. Purpose:
              The second level of the game challenges the player to eliminate double the number of enemies from levelone to progress to the next stage. 
              This level features enemy spawning where they appear based on a set probability. The player's kill count is tracked and scattershots available. 
              Hence, reaching the required target.

              - Configure the level environment by incorporating a unique background image and an indicator that pops up and fades showing what level it is.
              - Initialize the player and provide an on-screen display for the kill count and ScatterShot count.
              - Implement enemy spawning mechanics with a defined probability, ensuring a maximum limit on the number of active enemies.
              - Continuously monitor and update the player’s kill count to check if they have reached the target required to progress to the next level.

      6.1.3. Key Methods: -checkIfGameOver()
                          -initialzeFriendlyUnits()
                          -showLevelTwo(String levelName)
                          -spawnEnemyUnits()
                          -initializeKillCountText()
                          -instantiateLevelView()
                          -userHasReachedKillTarget()
                          -updatescene()
                          -updateKillCountText()

    6.2. Class: Level Three
    
      6.2.1. Location: com.example.demo.Levels
      
      6.2.2. Purpose:
              The third level of the game challenges the player to eliminate a enemy boss plane with 50 health to progress to the next stage. 
              This level features enemy boss that moves in a randomised up/down motion and shooting randomised maximum possible projectiles. 
              The enemy boss's health level is tracked so is your scattershots left, kill the boss.

              - Configure the level environment by incorporating a unique background image and an indicator that pops up and fades showing what level it is.
              - Initialize the player and provide an on-screen display for the boss health count and ScatterShot count.
              - Implement enemy boss with health 50
              - Overcome the conditions of the boss as it's movement is randomised, defeat the boss and move on to the next level.

      6.2.3. Key Methods: -checkIfGameOver()
                          -initialzeFriendlyUnits()
                          -showLevelThree(String levelName)
                          -spawnEnemyUnits()
                          -instantiateLevelView()
                          -updatescene()
                          -updateBossHealthCounter()
      
      6.3. Class: SceneController

        6.3.1. Location: com.example.demo.controller

        6.3.2. Purpose:
                Handles the bringing out the main menu and switching between the main menu to the game, as well as quit which closes the game

                - Configures the environment by incorporating an unique background and title.
                - Background music initialises
                - Play button to switch from main menu to the game
                - Quit button to close the game

        6.3.3. Key Methods: -setStage()
                            -intialize()
                            -handleStartGame()
                            -handleQuit()


  **7. Modified Java Classes**

    7.1. Main Class  
        7.1.1. Added Background Music  
               - Change: Added a call to `LevelParent.BGplaySound("/Music/BGM.wav")` to play background music when the game launches.  
               - Reason: To enhance the user experience by providing immersive audio as part of the game's main menu.  

        7.1.2. Integrated Main Menu Scene  
              - Change: Replaced direct controller initialization with the loading of an FXML file for the main menu. 
              - Reason: To implement a modular and scalable user interface using JavaFX FXML, making it easier to manage and update the main menu layout and functionality.  

        7.1.3. Linked Scene Controller  
               - Change: Configured the `SceneController` by passing the primary `Stage` to it using  
               - Reason: To manage transitions between different scenes (e.g., main menu, gameplay) and improve code modularity and reusability.  

        7.1.4. Removed Direct `Controller` Initialization  
               - Change: Removed the initialization of `Controller` (`myController = new Controller(stage);`) and its associated method `launchGame()`.  
               - Reason: Transitioned to a more structured design pattern by starting the game via the main menu and scene transitions rather than directly launching the                             game logic.  

    7.2. ActiveActor  
        7.2.1. Package Organization Updated  
               - Change: Moved the class to the `com.example.demo.ActiveActor` package.  
               - Reason: To improve code organization and clarity by categorizing classes into specific packages based on functionality.  

    7.3. ActiveActorDestructible  
        7.3.1. Added Collision Bounds Adjustment  
               - Change: Introduced the `getAdjustedBounds()` method to calculate collision bounds based on configurable shrink factors.  
               - Reason: To allow precise control over collision detection by adjusting the effective collision area of the actor.  
        
        7.3.2. Added Configurable Shrink Factors  
               - Change: Added `shrinkFactorWidth` and `shrinkFactorHeight` as properties to control collision bounds' width and height adjustments.  
               - Reason: To provide flexibility in defining the actor's collision area, improving collision accuracy for differently sized actors.  
        
        7.3.3. Overloaded Constructor with Default Shrink Factors  
               - Change: Created a new constructor with default shrink factors (`0.3` for both width and height).  
               - Reason: To simplify object creation when specific shrink factors are not provided, improving ease of use.  

    7.4. Boss Class
        7.4.1 Adjustments to Health and Shield Properties  
                - Change: Reduced HEALTH from 100 to 50 for level three.  
                - Reason: Balancing gameplay to make the boss less overpowered and more engaging.  
        
        7.4.2 Improved Shielding Mechanics  
                - Change: Refactored activateShield and deactivateShield methods to adjust collision bounds dynamically using setShrinkFactorHeight and setShrinkFactorWidth.  
                - Reason: Enhanced visual feedback and functionality by shrinking the collision area during shielding to reflect a defensive state.  
        
        7.4.3 Customizable Shield Activation  
                - Change: Made shieldShouldBeActivated a public method to allow more flexible interaction with external components.  
                - Reason: To provide external systems control over shield activation logic (e.g., event-based triggers).  
        
        7.4.4 Enhanced Projectile Firing Logic  
                - Change: The firing logic (bossFiresInCurrentFrame) remains unchanged but is complemented by improved documentation for clarity.  
                - Reason: Ensure readability and maintainability of the critical gameplay logic.  
        
        7.4.5 Movement Improvements  
                - Change: Refactored initializeMovePattern to ensure the boss's movements are sufficiently randomized within a predefined pattern.  
                - Reason: Increase unpredictability of boss movements, making gameplay more challenging.  
        
        7.4.6 Modularized Shield Frames Counter  
                - Change: Added a getter getFramesWithShieldActivated.  
                - Reason: To expose shield duration to external systems for monitoring or debugging.  

    7.5. EnemyPlane Class  
        7.5.1. Collision Bounds Adjustment  
                - Change: Added `setShrinkFactorHeight(0.25)` and `setShrinkFactorWidth(0.6)` in the constructor.  
                - Reason: Reduced the collision bounds of the plane for improved gameplay accuracy, ensuring that hit detection aligns better with the visual model. 
        
   
    7.6. FighterPlane  
        7.6.1. Package Organization  
               - Change: Moved the class to `com.example.demo.ActiveActor` package for consistency with the project structure.  
               - Reason: To align the class with the appropriate package for better project organization and consistency.   

    7.7. UserPlane  
        7.7.1 Enhanced Velocity Management  
               - Change: Replaced the integer `velocityMultiplier` with a `double` for more precise velocity control, including `currentVelocity` and `targetVelocity`.  
               - Reason: To allow smoother and more gradual movement of the plane with acceleration and deceleration, improving the responsiveness of user controls.  
        
        7.7.2 Introduced Acceleration and Deceleration  
               - Change: Added new constants `ACCELERATION` and `DECELERATION`, and logic for adjusting the velocity gradually.  
               - Reason: To provide more fluid movement, making the game feel more dynamic and responsive rather than just snapping between states.  
        
        7.7.3 Scatter Shot Mechanic Added  
               - Change: Introduced a new method `fireScatterShot()` that fires multiple projectiles in different directions.  
               - Reason: To offer the player more attack options, enhancing gameplay variety. The feature is limited by the number of available scatter shots.  
        
        7.7.4 Improved Movement Control  
               - Change: The movement logic was modified to use `targetVelocity` and `currentVelocity` to control vertical movement instead of direct setting of `velocityMultiplier`.  
               - Reason: This allows for smoother transitions in movement, avoiding abrupt stops and starts, making the controls feel more natural.  
        
        7.7.5 Health Management Enhancements  
               - Change: Introduced `setShrinkFactorWidth(0.6)` and `setShrinkFactorHeight(0.25)` to modify the size of the plane, likely reflecting a damage state or aesthetic choice.  
               - Reason: To visually indicate the player's plane condition and provide a more immersive experience.  
        
        7.7.6 Scatter Shot Use Limitation  
               - Change: Added a `scatterShotUses` counter to limit the number of times scatter shots can be used.  
               - Reason: To add a layer of strategy to the gameplay, forcing the player to use scatter shots wisely.  
        
        7.7.7 Simplified Projectile Firing  
               - Change: The `fireProjectile()` method was retained, but it now includes clearer logic for firing projectiles directly ahead of the plane.  
               - Reason: To maintain consistency in firing logic while enabling new attack mechanics like scatter shots.  
        
        7.7.8 Kill Tracking  
               - Change: Introduced `incrementKillCount()` and `getNumberOfKills()` to track and display the player's kill count.  
               - Reason: To enhance the game's competitive aspect and give the player more engagement with progress and achievements.

    



  **8. Unexpected problems**

      8.1. Time given
        8.1.1. Time given to understand and then refactor the game and implement wasn't enough, struggled doing so however classmates came together 
                to ask for extension time.
                            
