package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller for the “Reports” tab.
 * Extend this later with real report-generation logic (SQL queries, PDF export, etc.).
 */
public class ReportController {

    /* ---------- FXML-injected UI controls ---------- */

    @FXML private TableView<ReportRow> reportTable;
    @FXML private TableColumn<ReportRow, String> colName;
    @FXML private TableColumn<ReportRow, Number> colValue;
    @FXML private Label summaryLabel;

    /* ---------- Initialization ---------- */

    @FXML
    private void initialize() {
        // Wire columns to ReportRow properties (leave as comments until you add a data model)
        //
        // colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        // colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        //
        // reportTable.setItems(FXCollections.observableArrayList(
        //         new ReportRow("Total books",  1523),
        //         new ReportRow("Books on loan", 324),
        //         new ReportRow("Active users", 211)
        // ));
        //
        // summaryLabel.setText("3 KPIs loaded");
    }

    /* ---------- Optional inner record for quick prototyping ---------- */

    public record ReportRow(String name, Integer value) {}
}
