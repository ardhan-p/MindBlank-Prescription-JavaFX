package com.mindblank.pharmacist.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Pharmacist;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PharmacistMainMenuUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutBtn;
    @FXML private Button viewPresBtn;
    private Pharmacist pharm;

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
            loader.setLocation(Main.class.getResource("PharmacistMainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PharmacistMainMenuUI ui = loader.getController();
            ui.getPharmacistInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pharmacist Main Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets pharmacist's info from previous scene
    public void getPharmacistInfo(User u) {
        pharm = new Pharmacist(u);
        welcomeLabel.setText("Welcome " + pharm.getRealName());
    }

    // Side menu navigation
    public void searchPrescriptionOnClick(ActionEvent event){
        PharmacistSearchPrescriptionMenuUI.displayPage(event, pharm);
    }

    //Logout
    public void onLogout(ActionEvent event) {
        LoginUI.displayPage(event);
    }
}
