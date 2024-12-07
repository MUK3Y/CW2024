**1.** Github: https://github.com/MUK3Y/CW2024

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




  
