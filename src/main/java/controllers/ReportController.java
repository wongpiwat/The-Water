package controllers;

import databases.PostTreatmentDatabase;
import databases.PreTreatmentDatabase;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;

public class ReportController {
    private Account account;
    static databases.PreTreatmentDatabase PreTreatmentDatabase = new PreTreatmentDatabase();
    static PostTreatmentDatabase postTreatmentDatabase = new PostTreatmentDatabase();
    @FXML private ChoiceBox yearChoiceBox,monthChoiceBox;

    public void initialize() {
        monthChoiceBox.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "March", "May", "June", "July", "August", "September", "October", "November", "December"));
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
