package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.controllers.DoctorViewPrescriptionController;
import com.mindblank.entities.*;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorViewPrescriptionMenuUI {
    // side menu fxml
    @FXML private Button homeBtn;
    @FXML private Button addPresBtn;
    @FXML private Button addViewBtn;
    @FXML private Button updateProfileBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    // main pane fxml
    @FXML private Label patientLabel;
    @FXML private Label searchStatusLabel;
    @FXML private TextField patientInput;
    @FXML private Button searchBtn;
    @FXML private TableView<Prescription> prescriptionListTable;
    @FXML private TableColumn<Prescription, String> prescriptionTokenColumn;
    @FXML private TableColumn<Prescription, String> prescriptionDateColumn;

    private Doctor doc;
    private DoctorViewPrescriptionController doctorController;
    private ObservableList<Prescription> presObservableList = FXCollections.observableArrayList();
    private ObservableList<Medication> medicationObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));

        // set table manipulation access
        prescriptionTokenColumn.setCellValueFactory(new PropertyValueFactory<Prescription, String>("TokenString"));
        prescriptionDateColumn.setCellValueFactory(new PropertyValueFactory<Prescription, String>("Date"));
        prescriptionListTable.setItems(presObservableList);
    }

    // display page from previous scene
    // carries doctor data from previous scene to current scene
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("DoctorViewPrescriptionMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorViewPrescriptionMenuUI ui = loader.getController();
            ui.getDoctorInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ui functions

    // gets doctor info from previous scene
    private void getDoctorInfo(User u) {
        doc = new Doctor(u);
        doctorController = new DoctorViewPrescriptionController(doc);
    }

    // display user error message
    private void displayNotFound() {
        searchStatusLabel.setText("Patient not found!");
    }

    private void displayPatient(String patientIC) {
        prescriptionListTable.getItems().clear();
        doctorController.fetchUserPrescriptions(patientInput.getText(), presObservableList);
        searchStatusLabel.setText("Patient found!");
    }

    // side menu navigation on-click listeners

    @FXML
    public void homeOnClick(ActionEvent event) {
        DoctorMainMenuUI.displayPage(event, doc);
    }

    @FXML
    public void addPrescriptionOnClick(ActionEvent event) {
        DoctorAddPrescriptionMenuUI.displayPage(event, doc);
    }

    @FXML
    public void viewProfileOnClick(ActionEvent event) {

    }

    @FXML
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

    // main panel buttons on-click listeners
    @FXML
    public void onSubmit(ActionEvent event) {
        if (!doctorController.validatePatient(patientInput.getText())) {
            displayNotFound();
        } else {
            displayPatient(patientInput.getText());
        }
    }

    @FXML
    public void onRowSelect(MouseEvent event) {
        // gets selected row's token
        Prescription row = prescriptionListTable.getSelectionModel().getSelectedItem();
        String tokenString = row.getTokenString();

        // creates new patient from database query
        // gets date from database query
        // fills the medication list with medication that has the same token string as row
        // displays popup with above parameters as it gets passed via the function
        medicationObservableList.clear();
        Patient newPatient = doctorController.fetchPatientInfoInPrescription(tokenString);
        String prescriptionDate = doctorController.fetchPrescriptionDate(tokenString);
        doctorController.fetchSelectedMedicationInPrescription(tokenString, medicationObservableList);
        DoctorViewPrescriptionPopupUI.displayPage(event, tokenString, medicationObservableList, newPatient, prescriptionDate);
    }
}
