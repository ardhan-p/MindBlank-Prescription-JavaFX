package com.mindblank.patient.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.User;
import com.mindblank.patient.controllers.PatientSearchPrescriptionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.Objects;

public class PatientViewNewPrescriptionUI
{
    @FXML private Button homeBtn;
    @FXML private Button viewPastPresBtn;
    @FXML private Button viewNewPresBtn;
    @FXML private Button viewProfileBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label errorLabel;

    @FXML private TextField tokenNumber;
    @FXML private Button searchTokenNumber;

    private Patient pat;
    private PatientSearchPrescriptionController patientController;
    private ObservableList<Medication> medicationObservableList= FXCollections.observableArrayList();

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
    public void patientViewPastPresBtnOnClick(ActionEvent event)
    {
        PatientViewPastPrescriptionUI.displayPage(event,pat);
    }
    @FXML
    public void patientViewNewPresBtnOnClick(ActionEvent event)
    {
        PatientViewNewPrescriptionUI.displayPage(event,pat);
    }

    @FXML
    public void patientViewProfileBtnOnClick(ActionEvent event)
    {
        PatientViewProfileUI.displayPage(event,pat);
    }


    @FXML
    public void patientHomeBtnOnClick(ActionEvent event)
    {
        PatientMainMenuUI.displayPage(event,pat);
    }

    @FXML
    public void patientLogoutBtnOnClick(ActionEvent event)
    {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("LoginUI.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchTokenNumberOnClick(ActionEvent event)
    {
        if(!patientController.validateToken(tokenNumber.getText()))
        {
            errorLabel.setText("Invalid Token! Please try again!");
        }
    else
        {
            displayView(event);
        }
    }
    public void displayView(ActionEvent event)
    {
        medicationObservableList.clear();
        Patient newPatient = patientController.fetchPatientInfoInPrescription(tokenNumber.getText());
        System.out.println(newPatient.getRealName());
        String prescriptionDate = patientController.fetchPrescriptionDate(tokenNumber.getText());
        patientController.fetchUserMedication(tokenNumber.getText(), medicationObservableList);
        PatientViewPrescriptionPopupUI.displayPage1(event,tokenNumber.getText(), medicationObservableList, newPatient, prescriptionDate);
    }

    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("PatientViewNewPrescription.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientViewNewPrescriptionUI ui = loader.getController();
            ui.getPatientInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPatientInfo(User user)
    {
        pat = new Patient(user);
        patientController = new PatientSearchPrescriptionController(pat);
    }

}
