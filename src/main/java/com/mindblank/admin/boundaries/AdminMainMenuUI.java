package com.mindblank.admin.boundaries;

import com.mindblank.Main;
import com.mindblank.entities.Admin;
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

public class AdminMainMenuUI {
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutBtn;
    @FXML private Button addPresBtn;
    @FXML private Button viewPresBtn;
    @FXML private Button updateProfileBtn;

    private Admin admin;

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
            loader.setLocation(Main.class.getResource("AdminMainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AdminMainMenuUI ui = loader.getController();
            ui.getAdminInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Main Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets admin info from previous scene
    public void getAdminInfo(User u) {
        admin = new Admin(u);
        admin.setAdminInfoFromDB(u.getuName());
        welcomeLabel.setText("Welcome Admin " + admin.getRealName());
    }

    // action listeners
    public void addUserOnClick(ActionEvent event) {
        AdminAddUserMenuUI.displayPage(event, admin);
    }

    public void searchUserOnClick(ActionEvent event) {
        AdminSearchUserMenuUI.displayPage(event, admin);
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
