package controllers;

import databases.EventLogsDBConnector;
import databases.StandardDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Account;
import models.Standard;
import utilities.DateUtilities;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;

public class StandardController {
    private Account account;
    private Standard standard;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    @FXML private Label temperature,pH,dissolvedOxygen,mlss,name,date;
    @FXML private Button deleteButton,createButton;

    public void initialize() {
        standard = StandardDBConnector.getStandard();
        if (standard != null) {
            deleteButton.setDisable(false);
            createButton.setDisable(true);
            name.setText(standard.getName());
            date.setText(standard.getDate());
            temperature.setText(decimalFormat.format(standard.getTemperature()));
            pH.setText(decimalFormat.format(standard.getpH()));
            dissolvedOxygen.setText(decimalFormat.format(standard.getDissolvedOxygen()));
            mlss.setText(decimalFormat.format(standard.getMlss()));
        } else {
            deleteButton.setDisable(true);
            createButton.setDisable(false);
        }
    }

    public void createOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateStandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateStandardController createStandardController = loader.getController();
        createStandardController.setStandard(standard);
        createStandardController.setUser(account);
        stage.show();
    }

    public void deleteOnAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete standard?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("The Water");
        alert.setHeaderText("");
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Deleted standard","Standard");
            StandardDBConnector.deleteStandard();
            name.setText("");
            date.setText("");
            temperature.setText("");
            pH.setText("");
            dissolvedOxygen.setText("");
            mlss.setText("");
            deleteButton.setDisable(true);
            createButton.setDisable(false);
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
            informationAlert.setTitle("The Water");
            informationAlert.setHeaderText("");
            informationAlert.showAndWait();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}

