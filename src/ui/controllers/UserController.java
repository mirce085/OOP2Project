package ui.controllers;

import model.Role;
import model.User;
import service.UserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller for the Users tab.
 */
public class UserController {
    private final UserService userService = new UserService();

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private ComboBox<Role> roleCombo;
    @FXML private Button createButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    @FXML
    public void initialize() {
        roleCombo.setItems(FXCollections.observableArrayList(Role.values()));
    }

    @FXML
    private void onCreateUser() {
        try {
            User user = new User(
                    idField.getText(),
                    nameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    roleCombo.getValue()
            );
            userService.create(user);
            showAlert(Alert.AlertType.INFORMATION, "User Created", "User " + user.getId() + " created.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Creation Failed", e.getMessage());
        }
    }

    @FXML
    private void onUpdateUser() {
        try {
            User user = new User(
                    idField.getText(),
                    nameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    roleCombo.getValue()
            );
            userService.update(user);
            showAlert(Alert.AlertType.INFORMATION, "User Updated", "User " + user.getId() + " updated.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", e.getMessage());
        }
    }

    @FXML
    private void onDeleteUser() {
        try {
            String id = idField.getText();
            userService.delete(id);
            showAlert(Alert.AlertType.INFORMATION, "User Deleted", "User " + id + " deleted.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Deletion Failed", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}