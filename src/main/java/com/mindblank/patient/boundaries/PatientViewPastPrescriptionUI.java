package com.mindblank.patient.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Medication;
import com.mindblank.entities.Patient;
import com.mindblank.entities.Prescription;
import com.mindblank.login.LoginUI;
import com.mindblank.patient.controllers.PatientViewPrescriptionController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientViewPastPrescriptionUI {

    @FXML private Button homeBtn;
    @FXML private Button viewPastPresBtn;
    @FXML private Button viewNewPresBtn;
    @FXML private Button viewProfileBtn;
    @FXML private Button logout;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    //main pane fxml
    @FXML private TableView<Prescription> prescriptionListTable;
    @FXML private TableColumn<Prescription, String> prescriptionTokenColumn;
    @FXML private TableColumn<Prescription, String> prescriptionDateColumn;

    private Patient pat;
    private PatientViewPrescriptionController patientController;
    private ObservableList<Prescription> presObservableList= FXCollections.observableArrayList();
    private ObservableList<Medication> medicationObservableList=FXCollections.observableArrayList();

    public PatientViewPastPrescriptionUI() {

    }

    @FXML
    public void initialize() {
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

    public static void displayPage(ActionEvent event, Patient user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource(("PatientViewPastPrescription.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PatientViewPastPrescriptionUI ui = loader.getController();
            ui.getPatientInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Past Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPatientInfo(Patient u) {
        pat = new Patient(u);
        patientController = new PatientViewPrescriptionController(pat);
        displayPatient(pat.getuName());
    }

   private void displayPatient(String patientIC) {
       prescriptionListTable.getItems().clear();
       ArrayList<Prescription> prescriptionArrayList = patientController.fetchUserPrescriptions(patientIC);

       for (Prescription p : prescriptionArrayList) {
           presObservableList.add(p);
       }
   }

   @FXML
   public void patientHomeBtnOnClick(ActionEvent event)
   {
       PatientMainMenuUI.displayPage(event,pat);
   }

   @FXML
   public void patientViewProfileBtnOnClick(ActionEvent event)
   {
       PatientViewNewPrescriptionUI.displayPage(event,pat);
   }

   @FXML
   public void patientViewNewPresBtnOnClick(ActionEvent event) {
       PatientViewNewPrescriptionUI.displayPage(event,pat);
   }

   @FXML
    public void patientLogoutBtnOnClick(ActionEvent event) {
       LoginUI.displayPage(event);
    }


    @FXML
    public void onRowSelect(javafx.scene.input.MouseEvent mouseEvent) {
        // gets selected row's token
        Prescription row = prescriptionListTable.getSelectionModel().getSelectedItem();
        String tokenString = row.getTokenString();

        // creates new patient from database query
        // gets date from database query
        // fills the medication list with medication that has the same token string as row
        // displays popup with above parameters as it gets passed via the function
        medicationObservableList.clear();
        Patient newPatient = patientController.fetchPatientInfoInPrescription(tokenString);
        String prescriptionDate = patientController.fetchPrescriptionDate(tokenString);
        ArrayList<Medication> medArrayList = patientController.fetchSelectedMedicationInPrescription(tokenString);

        for (Medication m : medArrayList) {
            medicationObservableList.add(m);
        }

        PatientViewPrescriptionPopupUI.displayPage((javafx.scene.input.MouseEvent) mouseEvent, tokenString, medicationObservableList, newPatient, prescriptionDate);
    }
}
