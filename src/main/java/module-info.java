module com.example.test2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.test2 to javafx.fxml;
    exports com.example.test2;
    exports com.example.test2.controller;
    opens com.example.test2.controller to javafx.fxml;
    exports com.example.test2.dto;
    opens com.example.test2.dto to javafx.fxml;
}