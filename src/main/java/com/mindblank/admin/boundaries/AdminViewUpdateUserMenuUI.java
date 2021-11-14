package com.mindblank.admin.boundaries;

import com.mindblank.Main;
import com.mindblank.admin.controllers.AdminViewUpdateUserController;
import com.mindblank.entities.Admin;
import com.mindblank.entities.Patient;
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

public class AdminViewUpdateUserMenuUI {
    // side menu fxml
    @FXML
    private Button homeBtn;
    @FXML
    private Button searchUserBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;

    // main pane fxml
    @FXML
    private TextField userTypeField;
    @FXML
    private TextField nricField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private Button editBtn;
    @FXML
    private Button updateBtn;

    private Admin admin;
    private User searchedUser;
    private AdminViewUpdateUserController controller;
    private boolean editMode = false;

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
    public static void displayPage(ActionEvent event, User user, User searchedUser) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("AdminViewUpdateUserMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AdminViewUpdateUserMenuUI ui = loader.getController();
            ui.getAdminAndPatientInfo(user, searchedUser);
            ui.setUserFields();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("View / Update User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets admin and user objects from previous scene
    public void getAdminAndPatientInfo(User u, User searchedUser) {
        admin = new Admin(u);
        this.searchedUser = new User(searchedUser);
        admin.setAdminInfoFromDB(u.getuName());
        controller = new AdminViewUpdateUserController(admin);
    }

    // sets searched user object's info from previous scene into UI fields
    public void setUserFields() {
        userTypeField.setText(searchedUser.getUserType());
        nricField.setText(searchedUser.getuName());
        passField.setText(searchedUser.getuPass());
        nameField.setText(searchedUser.getRealName());
        emailField.setText(searchedUser.getEmail());
        phoneField.setText(searchedUser.getPhoneNum());
        addressField.setText(searchedUser.getAddress());
    }

    // if every field is NOT empty, valid == true
    // else false
    public boolean checkAllFields() {
        boolean valid = !nricField.getText().isEmpty() && !passField.getText().isEmpty()
                && !nameField.getText().isEmpty() && !emailField.getText().isEmpty()
                && !phoneField.getText().isEmpty() && !addressField.getText().isEmpty();

        return valid;
    }

    // enables edit mode if it is currently false, vice-versa
    public void editOnClick(ActionEvent event) {
        if (editMode) {
            passField.setDisable(true);
            nameField.setDisable(true);
            emailField.setDisable(true);
            phoneField.setDisable(true);
            addressField.setDisable(true);
            editMode = false;
        } else {
            passField.setDisable(false);
            nameField.setDisable(false);
            emailField.setDisable(false);
            phoneField.setDisable(false);
            addressField.setDisable(false);
            editMode = true;
        }
    }

    // displays error popup
    public void showErr() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error occurred!");
        alert.setContentText("Database update error has occurred");
        alert.showAndWait();
    }

    // displays success popup
    public void showSuccess(User u) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Successfully updated user.");
        alert.setContentText("User " + u.getuName() + " has been updated in the database!");
        alert.showAndWait();
    }

    // gets all the inputted fields and updates the searched user's info in the database
    public void updateOnClick(ActionEvent event) {
        User u = new User(nricField.getText(), passField.getText(), nameField.getText(), emailField.getText(),
                phoneField.getText(), addressField.getText(), userTypeField.getText());

        if (checkAllFields() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please fill in all of the fields");
            alert.showAndWait();
        } else {
            if (controller.updateUserInfo(u)) {
                passField.setDisable(true);
                nameField.setDisable(true);
                emailField.setDisable(true);
                phoneField.setDisable(true);
                addressField.setDisable(true);
                editMode = false;

                showSuccess(u);
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

    public void searchUserProfileOnClick(ActionEvent event) {
        AdminSearchUserMenuUI.displayPage(event, admin);
    }
}
