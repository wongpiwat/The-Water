package controllers;

import databases.StandardDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Standard;

import java.io.IOException;

public class HomeController {
    @FXML private Button preTreatmentButton,postTreatmentButton,accountsButton,reportButton;
    public void initialize() {
        Standard standard = StandardDB.loadStandard();
        if (standard==null) {
            preTreatmentButton.setDisable(true);
            postTreatmentButton.setDisable(true);
            accountsButton.setDisable(true);
            reportButton.setDisable(true);
        } else {
            preTreatmentButton.setDisable(false);
            postTreatmentButton.setDisable(false);
            accountsButton.setDisable(false);
            reportButton.setDisable(false);
        }
    }

    public void standardOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/standard.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void preTreatmentOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pre-treatment.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void postTreatmentOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/post-treatment.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void logoutOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void accountsOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void reportOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/report.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void aboutOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/about.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
