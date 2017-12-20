package controllers;

import utilities.CheckInput;
import utilities.DateUtilities;
import databases.TreatmentsDBConnector;
import models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreatePostTreatmentController {
    private Account account;
    @FXML private TextField volumeWater,temperature,pH,dissolvedOxygen,volumeSediment,mlss,electricity,deodorizerSystem;

    public void saveOnAction(ActionEvent event) throws IOException {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            List<Boolean> checkBoolean = new ArrayList<>();
            checkBoolean.add(CheckInput.isAllNumber(volumeWater));
            checkBoolean.add(CheckInput.isAllNumber(temperature));
            checkBoolean.add(CheckInput.isAllNumber(pH));
            checkBoolean.add(CheckInput.isAllNumber(dissolvedOxygen));
            checkBoolean.add(CheckInput.isAllNumber(volumeSediment));
            checkBoolean.add(CheckInput.isAllNumber(mlss));
            checkBoolean.add(CheckInput.isAllNumber(electricity));
            checkBoolean.add(CheckInput.isAllNumber(deodorizerSystem));
            List<String> checkTextField = new ArrayList<>();
            checkTextField.add(volumeWater.getText());
            checkTextField.add(temperature.getText());
            checkTextField.add(pH.getText());
            checkTextField.add(dissolvedOxygen.getText());
            checkTextField.add(volumeSediment.getText());
            checkTextField.add(mlss.getText());
            checkTextField.add(electricity.getText());
            checkTextField.add(deodorizerSystem.getText());
            if (CheckInput.isAllCorrectEmpty(checkTextField) && CheckInput.isAllCorrectType(checkBoolean)) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                TreatmentsDBConnector.savePostTreatment(DateUtilities.getDateNumber(),Double.parseDouble(volumeWater.getText()), Double.parseDouble(temperature.getText()), Double.parseDouble(pH.getText()), Double.parseDouble(dissolvedOxygen.getText()),Double.parseDouble(volumeSediment.getText()),Double.parseDouble(mlss.getText()),Double.parseDouble(electricity.getText()),Double.parseDouble(deodorizerSystem.getText()));
                volumeWater.setText("");
                temperature.setText("");
                pH.setText("");
                dissolvedOxygen.setText("");
                volumeSediment.setText("");
                mlss.setText("");
                electricity.setText("");
                deodorizerSystem.setText("");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentsController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }

}
