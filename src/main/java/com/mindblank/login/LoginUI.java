package com.mindblank.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginUI {
    @FXML
    private Button clearBtn;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label loginStatus;

    public void clear(ActionEvent event) {
        usernameInput.setText("");
        passwordInput.setText("");
        loginStatus.setText("Username and password cleared!");
    }

    public void onSubmit(ActionEvent event) {
        if (LoginController.validateUser(usernameInput.getText(), passwordInput.getText())) {
            //TODO: add code to allow users to access main menu
        } else {
            loginStatus.setText("Login details are incorrect! Try again.");
        }
    }
}