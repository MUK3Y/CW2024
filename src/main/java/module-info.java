/**
 * The module declaration for the `com.example.demo` module.
 * This module defines the required dependencies, as well as the packages
 * that are exported and opened for reflection by JavaFX.
 */
module com.example.demo {

    /**
     * Requires the `javafx.controls` module for JavaFX GUI components
     * such as buttons, labels, and other UI controls.
     */
    requires javafx.controls;

    /**
     * Requires the `javafx.fxml` module for JavaFX FXML functionality
     * for designing the user interface declaratively and binding it to Java code.
     */
    requires javafx.fxml;

    /**
     * Requires the `java.desktop` module, which provides desktop-related APIs,
     * including image I/O and other utilities needed for the application.
     */
    requires java.desktop;

    /**
     * Opens the `com.example.demo` package to `javafx.fxml` for reflection-based
     * loading of FXML files that define the user interface for the application.
     */
    opens com.example.demo to javafx.fxml;

    /**
     * Opens the `com.example.demo.controller` package to `javafx.fxml` for 
     * reflection-based loading of controller classes that handle user interactions.
     */
    opens com.example.demo.controller to javafx.fxml;

    /**
     * Exports the `com.example.demo.controller` package, making it accessible to other modules.
     * This allows other modules to access the controller classes directly.
     */
    exports com.example.demo.controller;

    /**
     * Opens the `com.example.demo.Levels` package to `javafx.fxml` for reflection-based
     * loading of level-related classes and scene configurations.
     */
    opens com.example.demo.Levels to javafx.fxml;

    /**
     * Opens the `com.example.demo.ActiveActor` package to `javafx.fxml` for reflection-based 
     * access to actor classes that represent objects in the game world.
     */
    opens com.example.demo.ActiveActor to javafx.fxml;

    /**
     * Opens the `com.example.demo.Projectiles` package to `javafx.fxml` for reflection-based
     * access to projectile-related classes that define the game project's objects and mechanics.
     */
    opens com.example.demo.Projectiles to javafx.fxml;

    /**
     * Opens the `com.example.demo.Displays` package to `javafx.fxml` for reflection-based
     * access to display-related classes that manage UI and game displays.
     */
    opens com.example.demo.Displays to javafx.fxml;
}
