package controllers;

import databases.EventLogsDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utilities.AccountManager;
import utilities.DateUtilities;

import java.io.IOException;

public class HomeController {
    @FXML private Label firstNameLabel, lastNameLabel;
    @FXML private Button accountsButton,eventLogButton;

    public void initialize() {
        firstNameLabel.setText(AccountManager.getAccount().getFirstName());
        lastNameLabel.setText(AccountManager.getAccount().getLastName());
        if (AccountManager.getAccount().getType().equals("Staff")) {
            accountsButton.setDisable(true);
            eventLogButton.setDisable(true);
        }
    }

    public void standardOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void treatmentsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void reportOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void accountsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void eventLogsOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EventLogsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void aboutOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AboutView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void logoutOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Logged out","Home");
    }
}
