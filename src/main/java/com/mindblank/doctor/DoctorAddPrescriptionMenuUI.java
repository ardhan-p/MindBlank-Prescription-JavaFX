package com.mindblank.doctor;

import com.mindblank.Main;
import com.mindblank.entities.Doctor;
import com.mindblank.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorAddPrescriptionMenuUI {
    // side menu fxml
    @FXML private Button homeBtn;
    @FXML private Button addPresBtn;
    @FXML private Button addViewBtn;
    @FXML private Button updatePresBtn;
    @FXML private Button updateProfileBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    // main pane fxml
    @FXML private TextField patientInput;
    @FXML private Button validateBtn;
    @FXML private DatePicker dateInput;
    @FXML private Label validateStatusLabel;
    @FXML private TableView medicationListTable;
    @FXML private TableColumn medicationNameColumn;
    @FXML private TableColumn medicationDoseColumn;
    @FXML private TableColumn medicationExpiryColumn;
    @FXML private TableColumn medicationInstructionsColumn;
    @FXML private Button addMedicationBtn;
    @FXML private Button confirmPrescriptionBtn;
    private Doctor doc;
    private DoctorController doctorController;
    private boolean validatedPatient;

    @FXML
    // set time data
    public void initialize() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));
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

    // ui functions
    private void getDoctorInfo(User u) {
        doc = new Doctor(u);
        doctorController = new DoctorController(doc);
    }

    private boolean checkPatient(String patientIC) {
        return doctorController.validatePatient(patientIC);
    }

    private void displayErr() {
        validatedPatient = false;
        validateStatusLabel.setText("Patient not found!");
        addMedicationBtn.setDisable(true);
        confirmPrescriptionBtn.setDisable(true);
    }

    private void displayValid() {
        validatedPatient = true;
        validateStatusLabel.setText("Patient found!");
        addMedicationBtn.setDisable(false);
        confirmPrescriptionBtn.setDisable(false);
    }


    // side menu navigation on-click listeners
    public void homeOnClick(ActionEvent event) {
        DoctorMainMenuUI.displayPage(event, doc);
    }

    public void viewPrescriptionsOnClick(ActionEvent event) {

    }

    public void updatePrescriptionOnClick(ActionEvent event) {

    }

    public void updateProfileOnClick(ActionEvent event) {

    }

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

    // main panel on-click listeners
    public void validateBtnOnClick(ActionEvent event) {
        if (checkPatient(patientInput.getText())) {
            displayValid();
        } else {
            displayErr();
        }
    }
}
