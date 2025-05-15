package ui.controllers;

import model.Loan;
import model.Receipt;
import service.LoanService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;

/**
 * Controller for the Loans tab.
 */
public class LoanController {
    private final LoanService loanService = new LoanService();

    @FXML private TextField userIdField;
    @FXML private TextField isbnField;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextField returnLoanIdField;

    @FXML
    private void onCheckout() {
        String userId = userIdField.getText().trim();
        String isbn = isbnField.getText().trim();
        if (userId.isEmpty() || isbn.isEmpty() || dueDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Input Required", "Please fill all fields for checkout.");
            return;
        }
        try {
            Date dueDate = Date.from(dueDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Loan loan = loanService.checkout(userId, isbn);
            showAlert(Alert.AlertType.INFORMATION, "Checked Out", "Loan ID: " + loan.getId());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Checkout Failed", e.getMessage());
        }
    }

    @FXML
    private void onCheckin() {
        String loanId = returnLoanIdField.getText().trim();
        if (loanId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Required", "Please enter Loan ID to return.");
            return;
        }
        try {
            Receipt receipt = loanService.checkin(loanId);
            showAlert(Alert.AlertType.INFORMATION, "Checked In", "Return Date: " + receipt.getReturnDate());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Checkin Failed", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
