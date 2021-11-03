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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminSearchUserMenuUI {
    // side menu fxml
    @FXML
    private Button homeBtn;
    @FXML private Button searchUserBtn;
    @FXML private Button logoutBtn;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    // main pane fxml
    @FXML private TextField nricInput;
    @FXML private Button searchBtn;

    private Admin admin;

    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));
    }

    // display page from previous scene
    // carries admin data from previous scene to current scene
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("AdminSearchUserMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AdminSearchUserMenuUI ui = loader.getController();
            ui.getAdminInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Add User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAdminInfo(User u) {
        admin = new Admin(u);
        admin.setAdminInfoFromDB(u.getuName());
    }

    public void createUserOnClick(ActionEvent event) {
        // TODO: add SQL query in controller and entity class
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

    public void homeOnClick(ActionEvent event) {
        AdminMainMenuUI.displayPage(event, admin);
    }

    public void addUserProfileOnClick(ActionEvent event) {
        AdminAddUserMenuUI.displayPage(event, admin);
    }

}
