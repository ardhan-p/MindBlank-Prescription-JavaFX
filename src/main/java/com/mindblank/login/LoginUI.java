package com.mindblank.login;

import com.mindblank.admin.boundaries.AdminMainMenuUI;
import com.mindblank.doctor.boundaries.DoctorMainMenuUI;
import com.mindblank.entities.User;
import com.mindblank.pharmacist.boundaries.PharmacistMainMenuUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginUI {
    @FXML private Button clearBtn;
    @FXML private Button loginBtn;
    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;
    @FXML private Label loginStatus;
    private User user;
    private LoginController loginController;

    public void clear(ActionEvent event) {
        usernameInput.setText("");
        passwordInput.setText("");
        loginStatus.setText("Username and password cleared!");
    }

    public void onSubmit(ActionEvent event) {
        user = new User(usernameInput.getText(), passwordInput.getText());
        loginController = new LoginController(user);

        if (loginController.validateUser()) {
            if (loginController.getUserType().contains("DOCTOR")) {
                DoctorMainMenuUI.displayPage(event, user);
            } else if (loginController.getUserType().contains("PATIENT")) {
                // TODO: add patient display page
            } else if (loginController.getUserType().contains("PHARMACIST")) {
                PharmacistMainMenuUI.displayPage(event, user);
            } else if (loginController.getUserType().contains("ADMIN")) {
                AdminMainMenuUI.displayPage(event, user);
            }
        } else {
            loginStatus.setText("Login details are incorrect! Try again.");
        }
    }
}