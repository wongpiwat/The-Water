package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public void salesOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/management.fxml"));
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


    public void aboutOnClick(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/about.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
