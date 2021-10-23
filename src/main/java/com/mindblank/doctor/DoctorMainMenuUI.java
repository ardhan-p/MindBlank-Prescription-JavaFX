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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        doc = new Doctor(u);
        welcomeLabel.setText("Welcome Dr. " + doc.getRealName());
    }

    // side menu navigation on-click listeners

    public void addPrescriptionOnClick(ActionEvent event) {
        DoctorAddPrescriptionMenuUI.displayPage(event, doc);
    }

    public void viewPrescriptionsOnClick(ActionEvent event) {

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
}
