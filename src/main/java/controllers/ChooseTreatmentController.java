package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;

public class ChooseTreatmentController {
    private Account account;

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        TreatmentController treatmentController = loader.getController();
        treatmentController.setUser(account);
        stage.show();
    }

    public void preTreatmentOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreatePreTreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreatePreTreatmentController createPreTreatmentController = loader.getController();
        createPreTreatmentController.setUser(account);
        stage.show();
    }

    public void postTreatmentOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreatePostTreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreatePostTreatmentController createPostTreatmentController = loader.getController();
        createPostTreatmentController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
