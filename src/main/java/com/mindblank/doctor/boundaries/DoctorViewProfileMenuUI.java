package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.controllers.DoctorViewPrescriptionController;
import com.mindblank.entities.Doctor;
import com.mindblank.entities.User;
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

public class DoctorViewProfileMenuUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private TextField nameText;
    @FXML private TextField NRICText;
    @FXML private TextField phoneText;
    @FXML private TextField emailText;
    @FXML private TextArea addressText;

    private Doctor doc;
    private DoctorViewPrescriptionController doctorController;

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
            loader.setLocation(Main.class.getResource("DoctorViewProfileMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorViewProfileMenuUI ui = loader.getController();
            ui.nameText.setText(user.getRealName());
            ui.NRICText.setText(user.getuName());
            ui.phoneText.setText(user.getPhoneNum());
            ui.emailText.setText(user.getEmail());
            ui.addressText.setText(user.getAddress());
            ui.getDoctorInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets doctor info from previous scene
    private void getDoctorInfo(User u) {
        doc = new Doctor(u);
        doctorController = new DoctorViewPrescriptionController(doc);
    }

    // side menu navigation on-click listeners

    public void homeOnClick(ActionEvent event) {
        DoctorMainMenuUI.displayPage(event, doc);
    }

    public void viewPrescriptionOnClick(ActionEvent event) {
        DoctorViewPrescriptionMenuUI.displayPage(event, doc);
    }

    public void addPrescriptionOnClick(ActionEvent event) {
        DoctorAddPrescriptionMenuUI.displayPage(event, doc);
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
}
