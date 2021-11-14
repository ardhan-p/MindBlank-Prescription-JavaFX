package com.mindblank.login;

import com.mindblank.Main;
import com.mindblank.admin.boundaries.AdminMainMenuUI;
import com.mindblank.doctor.boundaries.DoctorMainMenuUI;
import com.mindblank.doctor.boundaries.DoctorViewPrescriptionMenuUI;
import com.mindblank.entities.Patient;
import com.mindblank.entities.User;
import com.mindblank.patient.boundaries.PatientMainMenuUI;
import com.mindblank.pharmacist.boundaries.PharmacistMainMenuUI;
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

    public static void displayPage(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("LoginUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayErr() {
        loginStatus.setText("Login details are incorrect! Try again.");
    }

    public void displayDoctor(ActionEvent event, User user) {
        DoctorMainMenuUI.displayPage(event, user);
    }

    public void displayPatient(ActionEvent event, User user) {
        PatientMainMenuUI.displayPage(event,user);
    }

    public void displayPharmacist(ActionEvent event, User user) {
        PharmacistMainMenuUI.displayPage(event, user);
    }

    public void displayAdmin(ActionEvent event, User user) {
        AdminMainMenuUI.displayPage(event, user);
    }

    // initiates the login process
    // if the username is associated with a particular user type
    // then it will display the menu for that user type
    public void onSubmit(ActionEvent event) {
        user = new User(usernameInput.getText(), passwordInput.getText());
        loginController = new LoginController(user);

        if (loginController.validateUser()) {
            if (loginController.getUserType().contains("DOCTOR")) {
                displayDoctor(event, user);
            } else if (loginController.getUserType().contains("PATIENT")) {
                displayPatient(event, user);
            } else if (loginController.getUserType().contains("PHARMACIST")) {
                displayPharmacist(event, user);
            } else if (loginController.getUserType().contains("ADMIN")) {
                displayAdmin(event, user);
            }
        } else {
            displayErr();
        }
    }
}