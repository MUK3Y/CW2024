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
        7.7.1. Enhanced Velocity Management  
               - Change: Replaced the integer `velocityMultiplier` with a `double` for more precise velocity control, including `currentVelocity` and `targetVelocity`.  
               - Reason: To allow smoother and more gradual movement of the plane with acceleration and deceleration, improving the responsiveness of user controls.  
        
        7.7.2. Introduced Acceleration and Deceleration  
               - Change: Added new constants `ACCELERATION` and `DECELERATION`, and logic for adjusting the velocity gradually.  
               - Reason: To provide more fluid movement, making the game feel more dynamic and responsive rather than just snapping between states.  
        
        7.7.3. Scatter Shot Mechanic Added  
               - Change: Introduced a new method `fireScatterShot()` that fires multiple projectiles in different directions.  
               - Reason: To offer the player more attack options, enhancing gameplay variety. The feature is limited by the number of available scatter shots.  
        
        7.7.4. Improved Movement Control  
               - Change: The movement logic was modified to use `targetVelocity` and `currentVelocity` to control vertical movement instead of direct setting of `velocityMultiplier`.  
               - Reason: This allows for smoother transitions in movement, avoiding abrupt stops and starts, making the controls feel more natural.  
        
        7.7.5. Health Management Enhancements  
               - Change: Introduced `setShrinkFactorWidth(0.6)` and `setShrinkFactorHeight(0.25)` to modify the size of the plane, likely reflecting a damage state or aesthetic choice.  
               - Reason: To visually indicate the player's plane condition and provide a more immersive experience.  
        
        7.7.6. Scatter Shot Use Limitation  
               - Change: Added a `scatterShotUses` counter to limit the number of times scatter shots can be used.  
               - Reason: To add a layer of strategy to the gameplay, forcing the player to use scatter shots wisely.  
        
        7.7.7. Simplified Projectile Firing  
               - Change: The `fireProjectile()` method was retained, but it now includes clearer logic for firing projectiles directly ahead of the plane.  
               - Reason: To maintain consistency in firing logic while enabling new attack mechanics like scatter shots.  
        
        7.7.8. Kill Tracking  
               - Change: Introduced `incrementKillCount()` and `getNumberOfKills()` to track and display the player's kill count.  
               - Reason: To enhance the game's competitive aspect and give the player more engagement with progress and achievements.
    7.8. GameOverImage  
        7.8.1. Refactoring Package Structure  
               - Change: Moved the class to `com.example.demo.Displays` package for consistency with the project structure.  
               - Reason: To align the class with the appropriate package for better project organization and consistency.   

    7.9. LevelView  
        7.9.1. Refactored Package Structure  
               - Change: Moved the class from `com.example.demo` to `com.example.demo.Displays`.  
               - Reason: Organized the package structure to group display-related components, improving maintainability and clarity.  
        
        7.9.2. Introduced Background Images  
               - Change: Added two `ImageView` objects, `backgroundImageView` and `backgroundImageView2`, to represent the "Game Over" and "Win" background images.  
               - Reason: Improved visual feedback by displaying themed backgrounds for game-ending states, enhancing user experience.  
            
        7.9.3. Enhanced Win Screen  
               - Change: Updated `showWinImage()` to include the win background image along with the win graphic.  
               - Reason: Created a more visually appealing and polished win screen.  
        
        7.9.4. Enhanced Game Over Screen  
               - Change: Modified `showGameOverImage()` to include the lose background image and scaled the game over graphic for better alignment.  
               - Reason: Enhanced the game over screen’s visual consistency and usability.  
        
        7.9.5. Added Screen Dimensions  
               - Change: Introduced `screenWidth` and `screenHeight` variables to manage consistent scaling of backgrounds and other elements.  
               - Reason: Improved flexibility for adapting the UI to various screen sizes and resolutions.  
   
    7.10. HeartDisplay 
         7.10.1. Refactoring Package Structure  
                 - Change: Moved the class to `com.example.demo.Displays` package for consistency with the project structure.  
                 - Reason: To align the class with the appropriate package for better project organization and consistency. 
    
    7.11. LevelViewLevelTwo  
         7.11.1. Refactored Package Structure  
               - Change: Moved the class from `com.example.demo` to `com.example.demo.Displays`.  
               - Reason: Organized display-related components in a dedicated package to improve project structure and maintainability.  
            
         7.11.2. Enhanced Constructor  
               - Change: Updated the constructor to explicitly initialize the shield image and add it to the root group.  
               - Reason: Improved clarity and ensured proper initialization of all elements specific to this level.  

    7.12. ShieldImage  
        7.12.1. Refactored Package Structure  
               - **Change:** Moved the class from `com.example.demo` to `com.example.demo.Displays`.  
               - **Reason:** Organized display-related components in a dedicated package for better structure and maintainability.  
            
         7.12.3. Fixed Image Path  
               - **Change:** Updated the image resource path to use `"/com/example/demo/images/shield.png"` instead of the incorrect one.  
               - **Reason:** Corrected the image resource path for the shield image to ensure it loads the correct image file.  

    7.13. WinImage  
        7.13.1. Refactored Package Structure  
               - Change: Moved the class from `com.example.demo` to `com.example.demo.Displays`.  
               - Reason: Improved the organization of display-related components for better maintainability and clarity in project structure.  
            
        7.13.2. Updated Image Path  
               - Change: Changed the image to `"/com/example/demo/images/winBanner.png"` instead of the old one.  
               - Reason: Replaced the old youwin image to ensure a better display when won.  
            
        7.13.3. Adjusted Image Placement  
               - Change: Modified the `setLayoutX()` and `setLayoutY()` methods to adjust the position of the win image (`xPosition - 20` and `yPosition - 150`).  
               - Reason: Refined the position of the "win" image to provide a more visually pleasing placement on the screen.  
            
        7.13.4. Increased Image Size  
               - Change: Refactored the height of the "win" banner to 600 pixels while keeping the width at 600 pixels.  
               - Reason: Ensured the "win" banner has a larger, more prominent appearance in the game's UI for better visibility.  

    7.14. LevelBoss  
        7.14.1. Added Shield Functionality for Boss  
               - Change: Introduced ShieldImage to visually represent the boss's shield and manage its activation and deactivation in the updateScene method.  
               - Reason: Enhanced gameplay by adding a defensive mechanic for the boss, making the final level more challenging and dynamic.  

        7.14.2. Displayed Boss Health Counter  
               - Change: Added a Text element bossHealthCounter to show the boss's health dynamically. Implemented the updatebossHealthCounter method to keep it updated.  
               - Reason: Improved user experience by providing real-time feedback on the boss's remaining health.  

        7.14.3. Introduced Level Name Display with Effects  
               - Change: Added the showLevelBoss method to display the level name with a glowing text effect and fade-out animation.  
               - Reason: Created a dramatic and immersive introduction to the final boss level, enhancing player engagement.  

        7.14.4. Improved Game-Over Logic with Sound Effects  
               - Change: Played a victory sound effect when the boss is defeated and stopped the background music at game end.  
               - Reason: Enhanced the emotional impact of both victory and defeat scenarios.  

        7.14.5. Adjusted Enemy Spawning to Include Shield Visuals  
               - Change: Updated spawnEnemyUnits to add the shield image alongside the boss when enemies are spawned.  
               - Reason: Ensured proper initialization and visibility of the boss's shield during gameplay.  

        7.14.6. Refactored UI Initialization  
               - Change: Consolidated the setup of UI elements such as the boss health counter, shield image, and labels into separate methods.  
               - Reason: Improved code readability and modularity for better maintainability.  

        7.14.7. Renamed Class and Enhanced Documentation  
               - Change: Renamed the class from LevelTwo to LevelBoss.  
               - Reason: Provided better specificity and clarity for the class's role and functionality within the project.

    7.15. LevelOne  
        7.15.1. Enhanced Kill Count Display  
               - Change: Introduced a killCountText object to show the player’s current kills dynamically. Implemented initializeKillCountText and updateKillCountText methods to manage its initialization and updates.  
               - Reason: Provided real-time feedback to players on their progress towards the next level.  

        7.15.2. Added Level Name Display  
               - Change: Implemented the showLevelOne method to display the level name with glowing text and a fade-out animation.  
               - Reason: Enhanced player immersion and provided a clear introduction to Level One.  

        7.15.3. Refined Game-Over Logic  
               - Change: Stopped the timeline in checkIfGameOver when the player is destroyed or reaches the kill target.  
               - Reason: Prevented additional events from occurring after the game ends or transitions to the next level.  

        7.15.4. Improved UI Initialization  
               - Change: Consolidated the setup of UI components like the kill count display, level name, and labels into initializeFriendlyUnits.  
               - Reason: Streamlined the initialization process for better maintainability and readability.  

        7.15.5. Adjusted Background and Next Level Paths  
               - Change: Changed BACKGROUND_IMAGE_NAME to "/com/example/demo/images/BG1.jpg" and NEXT_LEVEL to "com.example.demo.Levels.LevelTwo".  
               - Reason: Ensured accurate references to resources and transitions and added a nicer background.  

        7.15.6. Improved Code Readability and Documentation  
               - Change: Added detailed comments and method-level documentation throughout the class.  
               - Reason: Enhanced clarity and understanding of the code's functionality for future developers.  

    



  **8. Unexpected problems**

      8.1. Time given
        8.1.1. Time given to understand and then refactor the game and implement wasn't enough, struggled doing so however classmates came together 
                to ask for extension time.
                            
