package ui.controllers;

import service.SettingsService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller for the Settings tab.
 */
public class SettingsController {
    private final SettingsService settingsService = new SettingsService();

    @FXML private TextField dbUrlField;
    @FXML private TextField dbUserField;
    @FXML private TextField dbPassField;
    @FXML private TextField smtpHostField;
    @FXML private TextField smtpPortField;
    @FXML private TextField smtpUserField;
    @FXML private TextField smtpPassField;
    @FXML private Button saveButton;

    @FXML
    private void onSaveSettings() {
        try {
            settingsService.save(
                    dbUrlField.getText(),
                    dbUserField.getText(),
                    dbPassField.getText(),
                    smtpHostField.getText(),
                    smtpPortField.getText(),
                    smtpUserField.getText(),
                    smtpPassField.getText()
            );
            showAlert(Alert.AlertType.INFORMATION, "Settings Saved", "Configuration updated successfully.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Save Failed", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
