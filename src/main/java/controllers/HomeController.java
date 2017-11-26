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

import java.io.IOException;

public class HomeController {
    private Account account;
    @FXML private Label firstNameLabel, lastNameLabel;
    @FXML private Button treatmentButton,reportButton,accountsButton,standardButton;

    public void initialize() {
        Standard standard = StandardDBConnector.loadStandardToTable();
        if (standard == null) {
            treatmentButton.setDisable(true);
            reportButton.setDisable(true);
        } else {
            treatmentButton.setDisable(false);
            reportButton.setDisable(false);
        }
    }

    public void standardOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        StandardController standardController = loader.getController();
        standardController.setUser(account);
        stage.show();
    }

    public void treatmentOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void accountsOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        AccountsController accountsController = loader.getController();
        accountsController.setUser(account);
        stage.show();
    }

    public void reportOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportView.fxml"));
        stage.setScene(new Scene(loader.load()));
        ReportController reportController = loader.getController();
        reportController.setUser(account);
        stage.show();
    }

    public void aboutOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AboutView.fxml"));
        stage.setScene(new Scene(loader.load()));
        AboutController aboutController = loader.getController();
        aboutController.setUser(account);
        stage.show();
    }

    public void logoutOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
        firstNameLabel.setText(account.getFirstName());
        lastNameLabel.setText(account.getLastName());
        if (account.getType().equals("Staff")) {
            accountsButton.setDisable(true);
        } else if (account.getType().equals("Supervisor")) {
            treatmentButton.setDisable(true);
            standardButton.setDisable(true);
        }
    }
}
