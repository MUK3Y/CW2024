module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.Levels to javafx.fxml;
    opens com.example.demo.ActiveActor to javafx.fxml;
    opens com.example.demo.Projectiles to javafx.fxml;
    opens com.example.demo.Displays to javafx.fxml;

}