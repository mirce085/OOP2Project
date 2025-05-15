package ui;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Original loading call:
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Updated: locate the FXML via the ClassLoader
        URL fxmlLocation = MainApp.class.getClassLoader().getResource("views/MainView.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException(
                    "Cannot find FXML resource 'views/MainView.fxml' on the classpath"
            );
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
