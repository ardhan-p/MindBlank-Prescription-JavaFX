package com.mindblank.login;

import com.mindblank.Main;
import com.mindblank.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
        SceneController newScene = new SceneController();
        if (LoginController.validateUser(usernameInput.getText(), passwordInput.getText())) {
            loginStatus.setText("Login details are correct!");
            if (LoginController.getUserType().contains("DOCTOR")) {
                try {
                    newScene.switchToScene(event, "DoctorMainMenu.fxml", "Doctor Main Menu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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