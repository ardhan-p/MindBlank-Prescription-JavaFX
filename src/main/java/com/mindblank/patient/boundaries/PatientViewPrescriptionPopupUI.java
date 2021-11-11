package com.mindblank.patient.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.patient.controllers.PatientViewPrescriptionController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientViewPrescriptionPopupUI {
    @FXML
    private TextField patientNameText;
    @FXML private TextField patientEmailText;
    @FXML private TextField patientICText;
    @FXML private TextField dateText;
    @FXML private Button closeBtn;
    @FXML private TableView<Medication> medicationTable;
    @FXML private TableColumn<Medication, String> medicineNameColumn;
    @FXML private TableColumn<Medication, Integer> dosageColumn;
    @FXML private TableColumn<Medication, String> expiryColumn;
    @FXML private TableColumn<Medication, String> instructionsColumn;

    private String currentTokenString;
    private PatientViewPrescriptionController patientController;
    private ObservableList<Medication> medicationObservableList;
    private Patient currentPatient;
    private String prescriptionDate;


    public void setCurrentTokenString(String currentTokenString) {
        this.currentTokenString = currentTokenString;
    }

    public void setPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public void setMedicationObservableList(ObservableList<Medication> medicationObservableList) {
        this.medicationObservableList = medicationObservableList;
    }

    public ObservableList<Medication> getMedicationObservableList() {
        return medicationObservableList;
    }

    @FXML
    public void initialize() {
        // set table manipulation access
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("MedicineName"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("Dosage"));
        expiryColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Expiry"));
        instructionsColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Instructions"));
    }

    // displays popup from view prescription menu
    // passed parameters include the prescription token, filled list of medications, patient object and prescription date
    public static void displayPage(MouseEvent event, String tokenString, ObservableList<Medication> medicationObservableList, Patient currentPatient, String prescriptionDate) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PatientViewPrescriptionPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientViewPrescriptionPopupUI ui = loader.getController();
            ui.setCurrentTokenString(tokenString);
            ui.setPatient(currentPatient);
            ui.patientNameText.setText(currentPatient.getRealName());
            ui.patientEmailText.setText(currentPatient.getEmail());
            ui.patientICText.setText(currentPatient.getuName());
            ui.dateText.setText(prescriptionDate);
            ui.medicationTable.setItems(medicationObservableList);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayPagePopup(ActionEvent event, String tokenString, ObservableList<Medication> medicationObservableList, Patient currentPatient, String prescriptionDate) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PatientViewPrescriptionPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientViewPrescriptionPopupUI ui = loader.getController();
            ui.setCurrentTokenString(tokenString);
            ui.setPatient(currentPatient);
            ui.patientNameText.setText(currentPatient.getRealName());
            ui.patientEmailText.setText(currentPatient.getEmail());
            ui.patientICText.setText(currentPatient.getuName());
            ui.dateText.setText(prescriptionDate);
            ui.medicationTable.setItems(medicationObservableList);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClose(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

