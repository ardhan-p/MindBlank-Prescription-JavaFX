package com.mindblank.pharmacist.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Pharmacist;
import com.mindblank.login.LoginUI;
import com.mindblank.pharmacist.controllers.PharmacistSearchPrescriptionController;
import com.mindblank.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.Date;

public class PharmacistSearchPrescriptionMenuUI {
    //Side Menu
    @FXML private Button homeBtn;
    @FXML private Button searchPresBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    //Main menu
    @FXML private TextField tokenInput;
    @FXML private Button searchBtn;
    @FXML private Label errorLabel;

    private Pharmacist pharm;
    private PharmacistSearchPrescriptionController pharmacistController;
    private ObservableList<Medication> medicationObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));
    }

    @FXML
    // initiates the process of validating a prescription token
    public void onSubmit(ActionEvent event){
        if (!pharmacistController.validateToken(tokenInput.getText())) {
            displayError();
        }
        else {
            displayView(event);
        }
    }

    public void displayError() {
        errorLabel.setText("Invalid Token! Please try again");
    }

    // displays the prescription that the pharmacist has searched
    public void displayView(ActionEvent event) {
        medicationObservableList.clear();
        Patient newPatient = pharmacistController.fetchPatientInfoInPrescription(tokenInput.getText());
        String prescriptionDate = pharmacistController.fetchPrescriptionDate(tokenInput.getText());
        ArrayList<Medication> medList = pharmacistController.fetchUserMedication(tokenInput.getText());
        for (Medication m : medList) {
            medicationObservableList.add(m);
        }
        PharmacistViewPrescriptionMenuUI.displayPage(event, pharm, tokenInput.getText(), medicationObservableList, newPatient, prescriptionDate);
    }

    // displays this page
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PharmacistSearchPrescriptionMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PharmacistSearchPrescriptionMenuUI ui = loader.getController();
            ui.getPharmacistInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // sets pharmacist's info from previous scene
    private void getPharmacistInfo(User u) {
        pharm = new Pharmacist(u);
        pharmacistController = new PharmacistSearchPrescriptionController(pharm);
    }

    // Side menu navigation
    public void homeOnClick(ActionEvent event){
        PharmacistMainMenuUI.displayPage(event, pharm);
    }

    //Logout
    public void onLogout(ActionEvent event) {
        LoginUI.displayPage(event);
    }
}
