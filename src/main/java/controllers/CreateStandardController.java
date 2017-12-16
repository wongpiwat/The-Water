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
import utilities.CheckInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateStandardController {
    private Account account;
    private Standard standard;
    @FXML private TextField temperature,pH,dissolvedOxygen,mlss;

    public void saveStandard(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("");
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            boolean isTemperature = CheckInput.isAllNumber(temperature);
            boolean isPH = CheckInput.isAllNumber(pH);
            boolean isDissolvedOxygen = CheckInput.isAllNumber(dissolvedOxygen);
            boolean isMLSS = CheckInput.isAllNumber(mlss);
            List<Boolean> check = new ArrayList<>();
            check.add(isTemperature);
            check.add(isPH);
            check.add(isDissolvedOxygen);
            check.add(isMLSS);
            if (!temperature.getText().equals("") && !pH.getText().equals("") && !dissolvedOxygen.getText().equals("") && !mlss.getText().equals("") && CheckInput.isAllCorrect(check)) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                StandardDBConnector.saveStandard(Double.parseDouble(temperature.getText()), Double.parseDouble(pH.getText()), Double.parseDouble(dissolvedOxygen.getText()), Double.parseDouble(mlss.getText()));
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
