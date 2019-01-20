package controllers;

import databases.StandardDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Account;
import models.Standard;
import utilities.AccountManager;

import java.io.IOException;

public class StandardController {
    private Standard standard;
    @FXML private Label temperature, pH, dissolvedOxygen, mlss, date, no, release;
    @FXML private Button editButton;
    public void initialize() {
        if (AccountManager.getAccount().getType().equals("Staff")) {
            editButton.setDisable(true);
        }
        standard = StandardDBConnector.getStandard();
        if (standard != null) {
            no.setText(standard.getNo()+"");
            release.setText(standard.getRelease());
            date.setText(standard.getDate());
            temperature.setText(String.format("%.2f",standard.getTemperature()));
            pH.setText(String.format("%.2f",standard.getpH()));
            dissolvedOxygen.setText(String.format("%.2f",standard.getDissolvedOxygen()));
            mlss.setText(String.format("%.2f",standard.getMlss()));
        }
    }

    public void editOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditStandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        EditStandardController editStandardController = loader.getController();
        editStandardController.setStandard(standard);
        stage.show();
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}

