package controllers;

import databases.StandardDBConnector;
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
import models.Standard;

import java.io.IOException;
import java.util.Optional;

public class CreateStandardController {
    private Account account;
    private Standard standard;
    @FXML private TextField pHTextField,bodTextField,sulfideTextField,settleableSolidsTextField,totalDissolvedSolidTextField,suspendedSoildsTextField,fatOilGreaseTextField,totalKjeldahlNitrogenTextField;

    public void saveStandard(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("");
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
                if (!pHTextField.getText().equals("") && !bodTextField.getText().equals("") && !sulfideTextField.getText().equals("") && !settleableSolidsTextField.getText().equals("") && !totalDissolvedSolidTextField.getText().equals("") && !suspendedSoildsTextField.getText().equals("") && !fatOilGreaseTextField.getText().equals("") && !totalKjeldahlNitrogenTextField.getText().equals("")) {
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                    StandardDBConnector.saveStandard(Double.parseDouble(pHTextField.getText()), Double.parseDouble(bodTextField.getText()), Double.parseDouble(sulfideTextField.getText()), Double.parseDouble(settleableSolidsTextField.getText()), Double.parseDouble(totalDissolvedSolidTextField.getText()), Double.parseDouble(suspendedSoildsTextField.getText()), Double.parseDouble(fatOilGreaseTextField.getText()), Double.parseDouble(totalKjeldahlNitrogenTextField.getText()));
                    backToStandard(event);
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not save because you fill in a form not complete.");
                    errorAlert.setTitle("The Water");
                    errorAlert.setHeaderText("");
                    errorAlert.showAndWait();
                }
            }
        }


    public void cancelToStandard(ActionEvent event) throws IOException {
        this.backToStandard(event);
    }

    public void backToStandard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StandardView.fxml"));
        stage.setScene(new Scene(loader.load(), 1080,600));
        StandardController standardController = loader.getController();
        standardController.setUser(account);
        stage.show();
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
