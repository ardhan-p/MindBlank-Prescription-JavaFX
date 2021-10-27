package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Prescription;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorViewPrescriptionPopupUI {
    @FXML private TextField patientNameText;
    @FXML private TextField patientEmailText;
    @FXML private TextField patientICText;
    @FXML private TextField dateText;
    @FXML private TableView<Medication> medicationTable;
    @FXML private TableColumn<Medication, String> medicineNameColumn;
    @FXML private TableColumn<Medication, Integer> dosageColumn;
    @FXML private TableColumn<Medication, String> expiryColumn;
    @FXML private TableColumn<Medication, String> instructionsColumn;

    private String currentTokenString;
    private ObservableList<Medication> medicationObservableList;

    public void setCurrentTokenString(String currentTokenString) {
        this.currentTokenString = currentTokenString;
    }

    @FXML
    public void initialize() {
        // set table manipulation access
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("MedicineName"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("Dosage"));
        expiryColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Expiry"));
        instructionsColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Instructions"));
        medicationTable.setItems(medicationObservableList);

        // set info from previous scene

    }

    // displays popup from view prescription menu
    public static void displayPage(ActionEvent event, String tokenString) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("DoctorViewPrescriptionPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorViewPrescriptionPopupUI ui = loader.getController();
            ui.setCurrentTokenString(tokenString);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("View Current Prescription");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
