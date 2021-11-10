package com.mindblank.doctor.boundaries;

import com.mindblank.Main;
import com.mindblank.doctor.controllers.DoctorAddPrescriptionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class DoctorAddQRCodePopupUI {
    @FXML private Label successLabel;
    @FXML private Button closeBtn;
    @FXML private ImageView qrCodeImageView;

    private DoctorAddPrescriptionController doctorController;

    // displays popup
    public static void displayPage(ActionEvent event, String tokenString, String successMessage, Image qrImage) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Main.class.getResource("DoctorAddQRCodePopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DoctorAddQRCodePopupUI ui = loader.getController();

            ui.successLabel.setText(successMessage);
            ui.qrCodeImageView.setImage(qrImage);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Success!");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClose(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
