package controllers;

import Utilities.DateUtilities;
import databases.PostTreatmentDatabase;
import models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CreatePostTreatmentController {
    private Account account;
    @FXML private TextField volumeWater,temperature,pH,dissolvedOxygen,volumeSediment,mlss,electricity,deodorizerSystem;

    public void saveItem(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            if (!volumeWater.getText().isEmpty() && !temperature.getText().isEmpty() && !pH.getText().isEmpty() && !dissolvedOxygen.getText().isEmpty() && !volumeSediment.getText().isEmpty() && !mlss.getText().isEmpty() && !electricity.getText().isEmpty() && !deodorizerSystem.getText().isEmpty()) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Information Dialog");
                informationAlert.setHeaderText("Look, an Information Dialog");
                informationAlert.setContentText("I have a great message for you!");
                informationAlert.showAndWait();
                PostTreatmentDatabase.saveItem(DateUtilities.getDateNumber(),Double.parseDouble(volumeWater.getText()), Double.parseDouble(temperature.getText()), Double.parseDouble(pH.getText()), Double.parseDouble(dissolvedOxygen.getText()),Double.parseDouble(volumeSediment.getText()),Double.parseDouble(mlss.getText()),Double.parseDouble(electricity.getText()),Double.parseDouble(deodorizerSystem.getText()));
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
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Ooops, there was an error!");
                errorAlert.setTitle("Error Dialog");
                errorAlert.setHeaderText("Look, an Error Dialog");
                errorAlert.showAndWait();
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choose-treatment.fxml"));
        stage.setScene(new Scene(loader.load()));
        ChooseTreatmentController chooseTreatmentController = loader.getController();
        chooseTreatmentController.setUser(account);
        stage.show();
    }

    public void backToTreatmentOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/treatment.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }

}
