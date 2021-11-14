package com.mindblank.patient.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Patient;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
import com.mindblank.patient.controllers.PatientViewPrescriptionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientViewProfileUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private TextField nameText;
    @FXML private TextField NRICText;
    @FXML private TextField phoneText;
    @FXML private TextField emailText;
    @FXML private TextArea addressText;

    private Patient pat;
    private PatientViewPrescriptionController patientController;

    @FXML
    public void initialize() {
        // set time data
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
            loader.setLocation(Main.class.getResource("PatientViewProfile.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientViewProfileUI ui = loader.getController();
            ui.nameText.setText(user.getRealName());
            ui.NRICText.setText(user.getuName());
            ui.phoneText.setText(user.getPhoneNum());
            ui.emailText.setText(user.getEmail());
            ui.addressText.setText(user.getAddress());
            ui.getPatientInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets patient info from previous scene
    private void getPatientInfo(User u) {
        pat = new Patient(u);
        patientController = new PatientViewPrescriptionController(pat);
    }

    // side menu navigation on-click listeners

    public void homeOnClick(ActionEvent event) {
        PatientMainMenuUI.displayPage(event, pat);
    }

    public void viewPastPresBtnOnClick(ActionEvent event) {PatientViewPastPrescriptionUI.displayPage(event, pat);
    }

    public void viewNewPresBtnOnClick(ActionEvent event) {
        PatientViewNewPrescriptionUI.displayPage(event, pat);
    }

    public void onLogout(ActionEvent event) {
        LoginUI.displayPage(event);
    }
}