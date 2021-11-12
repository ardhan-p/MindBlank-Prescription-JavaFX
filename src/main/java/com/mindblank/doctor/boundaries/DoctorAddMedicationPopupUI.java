package com.mindblank.doctor.boundaries;

import com.mindblank.DatabaseConnection;
import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Medicine;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class DoctorAddMedicationPopupUI {
    @FXML private ComboBox<String> selectMedicineInput;
    @FXML private TextField doseInput;
    @FXML private DatePicker expiryDateInput;
    @FXML private TextArea instructionsInput;
    @FXML private Label statusLabel;

    private ObservableList<Medication> medicationObservableList;

    // fills combobox (drop-down) from medication in SQL database
    @FXML
    public void initialize() {
        DatabaseConnection connectSQL = new DatabaseConnection();
        Connection connectDB = connectSQL.getConnection();

        String getMedicine = "SELECT * FROM MEDICINE ORDER BY medicineID;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getMedicine);

            while(queryResult.next()) {
                selectMedicineInput.getItems().add(queryResult.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // display popup from prescription menu
    // carries table data from previous scene
    public static void displayPage(ActionEvent event, ObservableList<Medication> medicationList) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("DoctorAddMedicationPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorAddMedicationPopupUI ui = loader.getController();
            ui.setMedicationList(medicationList);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Add Medication");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ui functions

    public void setMedicationList(ObservableList<Medication> medicationList) {
        this.medicationObservableList = medicationList;
    }

    // menu on-click listeners

    public void addMedicationOnClick(ActionEvent event) {
        if (selectMedicineInput.getSelectionModel().isEmpty() || doseInput.getText().isEmpty() ||
                expiryDateInput.getValue().toString().isEmpty() || instructionsInput.getText().isEmpty()) {
            statusLabel.setText("Missing data!");
        } else {
            Medicine newMedicine = new Medicine(selectMedicineInput.getSelectionModel().getSelectedIndex(),
                    selectMedicineInput.getSelectionModel().getSelectedItem());

            Medication newMedication = new Medication(newMedicine, Integer.parseInt(doseInput.getText()), instructionsInput.getText(),
                                                      expiryDateInput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            medicationObservableList.add(newMedication);

            Node source = (Node)event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
