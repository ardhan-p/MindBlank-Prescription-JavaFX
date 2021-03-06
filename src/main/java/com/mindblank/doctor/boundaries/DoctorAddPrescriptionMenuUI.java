package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.controllers.DoctorAddPrescriptionController;
import com.mindblank.entities.Doctor;
import com.mindblank.entities.Medication;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DoctorAddPrescriptionMenuUI {
    // side menu fxml
    @FXML private Button homeBtn;
    @FXML private Button addPresBtn;
    @FXML private Button addViewBtn;
    @FXML private Button updateProfileBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    // main pane fxml
    @FXML private TextField patientInput;
    @FXML private Button validateBtn;
    @FXML private DatePicker dateInput;
    @FXML private Label validateStatusLabel;
    @FXML private TableView<Medication> medicationListTable;
    @FXML private TableColumn<Medication, String> medicationNameColumn;
    @FXML private TableColumn<Medication, Integer> medicationDoseColumn;
    @FXML private TableColumn<Medication, String> medicationExpiryColumn;
    @FXML private TableColumn<Medication, String> medicationInstructionsColumn;
    @FXML private Button addMedicationBtn;
    @FXML private Button confirmPrescriptionBtn;

    private Doctor doc;
    private DoctorAddPrescriptionController doctorController;
    private ObservableList<Medication> medObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));

        // set table manipulation access
        medicationNameColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("MedicineName"));
        medicationDoseColumn.setCellValueFactory(new PropertyValueFactory<Medication, Integer>("Dosage"));
        medicationExpiryColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Expiry"));
        medicationInstructionsColumn.setCellValueFactory(new PropertyValueFactory<Medication, String>("Instructions"));
        medicationListTable.setItems(medObservableList);
    }

    // display page from previous scene
    // carries doctor data from previous scene to current scene
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("DoctorAddPrescriptionMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorAddPrescriptionMenuUI ui = loader.getController();
            ui.getDoctorInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Add Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets doctor info from previous scene
    private void getDoctorInfo(User u) {
        doc = new Doctor(u);
        doctorController = new DoctorAddPrescriptionController(doc);
    }

    // checks if patient exists
    private boolean checkPatient(String patientIC) {
        return doctorController.validatePatient(patientIC);
    }

    // changes status label to indicate error
    private void displayErr() {
        validateStatusLabel.setText("Patient not found!");
        addMedicationBtn.setDisable(true);
        confirmPrescriptionBtn.setDisable(true);
    }

    // changes status label to indicate valid user
    private void displayValid() {
        validateStatusLabel.setText("Patient found!");
        addMedicationBtn.setDisable(false);
    }

    @FXML
    // opens popup to add medication to prescription
    private void displayMediPopup(ActionEvent event) {
        DoctorAddMedicationPopupUI.displayPage(event, medObservableList);
    }


    public void homeOnClick(ActionEvent event) {
        DoctorMainMenuUI.displayPage(event, doc);
    }

    public void viewPrescriptionOnClick(ActionEvent event) {
        DoctorViewPrescriptionMenuUI.displayPage(event, doc);
    }

    public void viewProfileOnClick(ActionEvent event) {
        DoctorViewProfileMenuUI.displayPage(event, doc);
    }

    public void onLogout(ActionEvent event) {
        LoginUI.displayPage(event);
    }

    // checks database whether the inputted nric is valid and exists in db
    public void validateBtnOnClick(ActionEvent event) {
        if (checkPatient(patientInput.getText())) {
            displayValid();
        } else {
            displayErr();
        }
    }

    // displays popup which enables user to add medication to prescription
    public void addMedicationOnClick(ActionEvent event) {
        displayMediPopup(event);
        confirmPrescriptionBtn.setDisable(false);
    }

    // randomly generates token to give identification to new prescription
    public String createToken() {
        return doctorController.generateToken();
    }

    // returns true if prescription has been successfully added to the database
    public boolean addCurrentPrescription(String patientIC, String token, String date, ArrayList<Medication> medList) {
        return doctorController.addPrescription(patientIC, token, date, medList);
    }

    // displays popup to show user that confirmation has been sent
    public void displaySentPopup(ActionEvent event, String tokenString, String successPopupMessage, Image qrImage) {
        DoctorAddQRCodePopupUI.displayPage(event, tokenString, successPopupMessage, qrImage);
    }

    // initiates the insertion of prescription into the database
    public void confirmPrescriptionOnClick(ActionEvent event) {
        Medication currentMed;
        ArrayList<Medication> medArrayList = new ArrayList<Medication>();

        // gets all medication objects from observable list to array list
        for (int i = 0; i < medObservableList.size(); i++) {
            currentMed = medObservableList.get(i);
            medArrayList.add(currentMed);
        }

        // creates token for prescription
        String tokenString = createToken();

        if (addCurrentPrescription(patientInput.getText(), tokenString, dateInput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), medArrayList)) {
            // create success message for popup
            String successPopupMessage = "Email was successfully sent to " +
                    doctorController.fetchPatientEmailFromToken(tokenString) + " with the token " +
                    tokenString + " and with the QR code below.";

            // generate qr code for popup
            File qrFile = doctorController.generateQR(tokenString);
            Image qrImage = new Image(qrFile.toURI().toString());

            // sent confirmation successful popup
            displaySentPopup(event, tokenString, successPopupMessage, qrImage);

            // clears input elements
            patientInput.clear();
            dateInput.getEditor().clear();
            medicationListTable.getItems().clear();
            medObservableList.clear();
            addMedicationBtn.setDisable(true);
            confirmPrescriptionBtn.setDisable(true);
        } else {
            validateStatusLabel.setText("Unsuccessful confirmation, try again");
        }
    }
}
