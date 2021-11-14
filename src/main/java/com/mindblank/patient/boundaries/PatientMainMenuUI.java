package com.mindblank.patient.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.boundaries.DoctorAddPrescriptionMenuUI;
import com.mindblank.doctor.boundaries.DoctorMainMenuUI;
import com.mindblank.doctor.boundaries.DoctorViewPrescriptionMenuUI;
import com.mindblank.entities.Doctor;
import com.mindblank.entities.Patient;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
import com.mindblank.patient.controllers.PatientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientMainMenuUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutBtn;
    @FXML private Button addPresBtn;
    @FXML private Button viewPresBtn;
    @FXML private Button viewProfileBtn;
    private Patient pat;
    private PatientController controller;

    @FXML
    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));
    }

    // display main menu
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PatientMainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientMainMenuUI ui = loader.getController();
            ui.getPatientInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Patient Main Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets patient info from previous scene
    public void getPatientInfo(User u) {
        pat = new Patient();
        controller = new PatientController(pat);
        pat = controller.fetchPatientInfo(u.getuName());
        welcomeLabel.setText("Welcome  " + pat.getRealName());
    }

    // side menu functions
    public void viewPastPrescriptionOnClick(ActionEvent event) {
        PatientViewPastPrescriptionUI.displayPage(event, pat);
    }

    public void viewNewPrescriptionOnClick(ActionEvent event) {
        PatientViewNewPrescriptionUI.displayPage(event, pat);
    }

    public void patientViewProfileOnClick(ActionEvent event) {
        PatientViewProfileUI.displayPage(event, pat);
    }

    public void patientLogoutOnClick(ActionEvent event) {
        LoginUI.displayPage(event);
    }
}
