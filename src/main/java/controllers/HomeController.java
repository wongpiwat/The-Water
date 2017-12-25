package controllers;

import databases.EventLogsDBConnector;
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
import utilities.DateUtilities;

import java.io.IOException;

public class HomeController {
    private Account account;
    @FXML private Label firstNameLabel, lastNameLabel;
    @FXML private Button treatmentsButton,reportButton,accountsButton,standardButton,eventLogButton;

    public void initialize() {
        Standard standard = StandardDBConnector.getStandard();
        if (standard == null) {
            treatmentsButton.setDisable(true);
            reportButton.setDisable(true);
        } else {
            treatmentsButton.setDisable(false);
            reportButton.setDisable(false);
        }
    }

    public void standardOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        StandardController standardController = loader.getController();
        standardController.setUser(account);
        stage.show();
    }

    public void treatmentsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentsController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void reportOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportView.fxml"));
        stage.setScene(new Scene(loader.load()));
        ReportController reportController = loader.getController();
        reportController.setUser(account);
        stage.show();
    }

    public void accountsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        AccountsController accountsController = loader.getController();
        accountsController.setUser(account);
        stage.show();
    }

    public void eventLogsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EventLogsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        EventLogsController eventLogsController = loader.getController();
        eventLogsController.setUser(account);
        stage.show();
    }

    public void aboutOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AboutView.fxml"));
        stage.setScene(new Scene(loader.load()));
        AboutController aboutController = loader.getController();
        aboutController.setUser(account);
        stage.show();
    }

    public void logoutOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Logged out");
    }

    public void setUser(Account account) {
        this.account = account;
        firstNameLabel.setText(account.getFirstName());
        lastNameLabel.setText(account.getLastName());
        if (account.getType().equals("Staff")) {
            accountsButton.setDisable(true);
            eventLogButton.setDisable(true);
        } else if (account.getType().equals("Supervisor")) {
            treatmentsButton.setDisable(true);
            standardButton.setDisable(true);
        }
    }
}
