package com.mindblank.pharmacist.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.boundaries.DoctorViewPrescriptionMenuUI;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Pharmacist;
import com.mindblank.entities.User;
import com.mindblank.pharmacist.controllers.PharmacistSearchPrescriptionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PharmacistViewPrescriptionMenuUI {
    //Side Menu
    @FXML private Button homeBtn;
    @FXML private Button searchPresBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    //Main menu
    @FXML private TextField patientNameText;
    @FXML private TextField patientEmailText;
    @FXML private TextField patientICText;
    @FXML private TextField dateText;
    @FXML private Button updatePresBtn;
    @FXML private Label statusLabel;
    @FXML private TableView<Medication> medicationTable;
    @FXML private TableColumn<Medication, String> medicineNameColumn;
    @FXML private TableColumn<Medication, Integer> dosageColumn;
    @FXML private TableColumn<Medication, String> expiryColumn;
    @FXML private TableColumn<Medication, String> instructionsColumn;

    private String currentTokenString;
    private Patient currentPatient;
    private ObservableList<Medication> medicationObservableList;
    private Pharmacist pharm;
    private PharmacistSearchPrescriptionController pharmacistController;

    //Initializing alert object
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        dateLabel.setText(dateFormat.format(date));
        timeLabel.setText(timeFormat.format(date));

        // set table manipulation access
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("MedicineName"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("Dosage"));
        expiryColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Expiry"));
        instructionsColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Instructions"));
        medicationTable.setItems(medicationObservableList);
    }

    public static void displayPage(ActionEvent event, User user, String tokenString, ObservableList<Medication> medicationObservableList, Patient currentPatient, String prescriptionDate) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PharmacistViewPrescriptionMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PharmacistViewPrescriptionMenuUI ui = loader.getController();
            ui.getPharmacistInfo(user);
            ui.setCurrentTokenString(tokenString);
            ui.patientNameText.setText(currentPatient.getRealName());
            ui.patientEmailText.setText(currentPatient.getEmail());
            ui.patientICText.setText(currentPatient.getuName());
            ui.dateText.setText(prescriptionDate);
            ui.medicationTable.setItems(medicationObservableList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPharmacistInfo(User u){
        pharm = new Pharmacist(u);
        pharmacistController = new PharmacistSearchPrescriptionController(pharm);
    }

    //Update button
    @FXML
    public void onUpdate (ActionEvent event){
        //Creates an alert popup for confirmation
        alert.setTitle("Collection Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Patient has collected their medication successfully!");
        alert.showAndWait();
        //Code for database update
        boolean status = pharmacistController.updatePrescriptionStatus(currentTokenString);
        if (status == true){
            statusLabel.setText("Status: Collected");
        }
    }

    // Side menu navigation
    public void homeOnClick(ActionEvent event){
        PharmacistMainMenuUI.displayPage(event, pharm);
    }

    //Logout
    public void onLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("LoginUI.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
