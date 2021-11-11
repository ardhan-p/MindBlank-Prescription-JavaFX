package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.controllers.DoctorController;
import com.mindblank.doctor.controllers.DoctorViewProfileController;
import com.mindblank.entities.Doctor;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorMainMenuUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutBtn;
    @FXML private Button addPresBtn;
    @FXML private Button viewPresBtn;
    @FXML private Button updateProfileBtn;
    private Doctor doc;
    private DoctorViewProfileController doctorController;

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
            loader.setLocation(Main.class.getResource("DoctorMainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorMainMenuUI ui = loader.getController();
            ui.getDoctorInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Doctor Main Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets doctor info from previous scene
    public void getDoctorInfo(User u) {
        doc = new Doctor();
        doctorController = new DoctorViewProfileController(doc);
        doc = doctorController.fetchDoctorInfo(u.getuName());
        welcomeLabel.setText("Welcome Dr. " + doc.getRealName());
    }

    // side menu navigation on-click listeners

    public void addPrescriptionOnClick(ActionEvent event) {
        DoctorAddPrescriptionMenuUI.displayPage(event, doc);
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
}
