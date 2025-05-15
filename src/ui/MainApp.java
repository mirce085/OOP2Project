package ui;

import java.net.URL;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("/MainView.fxml");   // ①
        Objects.requireNonNull(fxml, "MainView.fxml not found on class-path");

        Parent root = FXMLLoader.load(fxml);
        stage.setScene(new Scene(root));
        stage.setTitle("Library Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
