package com.mindblank.admin.boundaries;

import com.mindblank.Main;
import com.mindblank.admin.controllers.AdminSearchUserController;
import com.mindblank.admin.controllers.AdminViewUpdateUserController;
import com.mindblank.entities.Admin;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
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
    private User user;
    private AdminSearchUserController adminController;

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
            stage.setTitle("Search User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets admin object from previous scene
    // creates controller using admin object
    public void getAdminInfo(User u) {
        admin = new Admin(u);
        admin.setAdminInfoFromDB(u.getuName());
        adminController = new AdminSearchUserController(admin);
    }

    // displays error popup
    public void showErr() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error occurred!");
        alert.setContentText("User NRIC not found!");
        alert.showAndWait();
    }

    // displays valid popup with user info
    public void showValid(ActionEvent event, String NRIC) {
        user = new User();
        AdminViewUpdateUserController viewController = new AdminViewUpdateUserController(admin);
        user = viewController.getUserFromDB(NRIC);
        AdminViewUpdateUserMenuUI.displayPage(event, admin, user);
    }

    // if search field is empty, displays error
    // else it would check the database whether nric is valid
    public void searchUserOnClick(ActionEvent event) {
        if (nricInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please fill in NRIC!");
            alert.showAndWait();
        } else {
            if (adminController.validateNRIC(nricInput.getText())) {
                showValid(event, nricInput.getText());
            } else {
                showErr();
            }
        }
    }

    public void onLogout(ActionEvent event) {
        LoginUI.displayPage(event);
    }

    public void homeOnClick(ActionEvent event) {
        AdminMainMenuUI.displayPage(event, admin);
    }

    public void addUserProfileOnClick(ActionEvent event) {
        AdminAddUserMenuUI.displayPage(event, admin);
    }

}
