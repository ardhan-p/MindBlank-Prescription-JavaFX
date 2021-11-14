package com.mindblank.admin.boundaries;

import com.mindblank.Main;
import com.mindblank.admin.controllers.AdminAddUserController;
import com.mindblank.entities.Admin;
import com.mindblank.entities.User;
import com.mindblank.login.LoginUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AdminAddUserMenuUI {
    // side menu fxml
    @FXML private Button homeBtn;
    @FXML private Button searchUserBtn;
    @FXML private Button logoutBtn;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    // main pane fxml
    @FXML private ComboBox<String> userTypeBox;
    @FXML private TextField nricField;
    @FXML private PasswordField passField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private Button createUserBtn;

    private Admin admin;
    private AdminAddUserController adminController;

    public void initialize() {
        // set time data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        timeLabel.setText(timeFormat.format(date));
        dateLabel.setText(dateFormat.format(date));

        // fill combobox of user
        ObservableList<String> userTypes = FXCollections.observableArrayList(
                "PATIENT",
                "DOCTOR",
                "PHARMACIST"
        );
        userTypeBox.setItems(userTypes);
    }

    // display page from previous scene
    // carries admin data from previous scene to current scene
    public static void displayPage(ActionEvent event, User user) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("AdminAddUserMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            AdminAddUserMenuUI ui = loader.getController();
            ui.getAdminInfo(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Add User");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get admin info from previous scene
    public void getAdminInfo(User u) {
        admin = new Admin(u);
        admin.setAdminInfoFromDB(u.getuName());
        adminController = new AdminAddUserController(admin);
    }

    // if every field is NOT empty, valid == true
    // else false
    public boolean checkAllFields() {
        boolean valid = !userTypeBox.getSelectionModel().isEmpty() && !nricField.getText().isEmpty() && !passField.getText().isEmpty()
                        && !nameField.getText().isEmpty() && !emailField.getText().isEmpty()
                        && !phoneField.getText().isEmpty() && !addressField.getText().isEmpty();

        return valid;
    }

    // clears all input fields
    public void clearAllFields() {
        nricField.clear();
        passField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        nricField.clear();
        userTypeBox.getSelectionModel().clearSelection();
    }

    // displays error pop-up
    public void showErr() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error occurred!");
        alert.setContentText("User NRIC is already in system!");
        alert.showAndWait();
    }

    // displays valid pop-up
    public void showValid(User u) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Successfully added user.");
        alert.setContentText("User " + u.getuName() + " has been added to the database!");
        alert.showAndWait();
        clearAllFields();
    }

    // creates user from user inputs
    // checks if fields are filled
    // checks if nric has existed
    // checks if database insertion has thrown error
    public void createUserOnClick(ActionEvent event) {
        User u = new User(nricField.getText(), passField.getText(), nameField.getText(), emailField.getText(),
                 phoneField.getText(), addressField.getText(), userTypeBox.getValue());

        if (checkAllFields() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please fill in all of the fields");
            alert.showAndWait();
        } else {
            if (adminController.validateNRIC(nricField.getText())) {
                if (adminController.addUserToDB(u)) {
                    showValid(u);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error occurred!");
                    alert.setContentText("Database insertion error has occurred");
                    alert.showAndWait();
                }
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

    public void searchUserProfileOnClick(ActionEvent event) {
        AdminSearchUserMenuUI.displayPage(event, admin);
    }
}