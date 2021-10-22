package com.mindblank.login;

import com.mindblank.doctor.DoctorMainMenuUI;
import com.mindblank.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginUI {

    @FXML
    private Button clearBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label loginStatus;

    public TextField getUsernameInput() {
        return usernameInput;
    }

    public void clear(ActionEvent event) {
        usernameInput.setText("");
        passwordInput.setText("");
        loginStatus.setText("Username and password cleared!");
    }

    // TODO: add switch screen functionality
    public void onSubmit(ActionEvent event) {
        User user = new User(usernameInput.getText(), passwordInput.getText());

        if (LoginController.validateUser(user)) {
            if (LoginController.getUserType(user).contains("DOCTOR")) {
                DoctorMainMenuUI.displayDoctorMainMenu(event, user);
            } else if (LoginController.getUserType(user).contains("PATIENT")) {

            } else if (LoginController.getUserType(user).contains("PHARMACIST")) {

            } else if (LoginController.getUserType(user).contains("ADMIN")) {

            } else if (usernameInput.getText().trim().isEmpty()) {
                loginStatus.setText("Username is required");
            } else if (passwordInput.getText().trim().isEmpty()) {
                loginStatus.setText("Password is required");
            } else {
                loginStatus.setText("Login details are incorrect! Try again." + usernameInput.getText());
            }
        }
    }
}