package controllers;

import databases.TreatmentDBConnector;
import utilities.DateUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;
import java.util.Optional;

public class CreatePreTreatmentController {
    private Account account;
    @FXML private TextField volumeWater,temperature,pH,dissolvedOxygen,mlss;

    public void saveItem(ActionEvent event) throws IOException {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        ConfirmationAlert.setTitle("The Water");
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            if (!volumeWater.getText().isEmpty() && !temperature.getText().isEmpty() && !pH.getText().isEmpty() && !dissolvedOxygen.getText().isEmpty() && !mlss.getText().isEmpty()) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                TreatmentDBConnector.savePreTreatment(DateUtilities.getDateNumber(),Double.parseDouble(volumeWater.getText()), Double.parseDouble(temperature.getText()), Double.parseDouble(pH.getText()), Double.parseDouble(dissolvedOxygen.getText()),Double.parseDouble(mlss.getText()));
                volumeWater.setText("");
                temperature.setText("");
                pH.setText("");
                dissolvedOxygen.setText("");
                mlss.setText("");
                backToTreatmentOnAction(event);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not save. Please fill out these fields and click save changes.");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseTreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        ChooseTreatmentController chooseTreatmentController = loader.getController();
        chooseTreatmentController.setUser(account);
        stage.show();
    }

    public void backToTreatmentOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}