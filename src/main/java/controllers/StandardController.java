package controllers;

import databases.StandardDB;
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

public class StandardController {
    private Account account;
    private Standard standard;
    @FXML private Label pH,bod,sulfide,settleableSolids,totalDissolvedSolid,suspendedSoilds,fatOilGrease,totalKjeldahlNitrogen;
    @FXML private Button deleteButton,createButton;

    public void initialize() {
        standard = StandardDB.loadStandardToTable();
        if (standard != null) {
            deleteButton.setDisable(false);
            createButton.setDisable(true);
            pH.setText(standard.getpH()+"");
            bod.setText(standard.getBod()+"");
            sulfide.setText(standard.getSulfide()+"");
            settleableSolids.setText(standard.getSettleableSolids()+"");
            totalDissolvedSolid.setText(standard.getTotalDissolvedSolid()+"");
            suspendedSoilds.setText(standard.getSuspendedSoilds()+"");
            fatOilGrease.setText(standard.getFatOilGrease()+"");
            totalKjeldahlNitrogen.setText(standard.getTotalKjeldahlNitrogen()+"");
        } else {
            deleteButton.setDisable(true);
            createButton.setDisable(false);
        }
    }

    public void createStandard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/standard-create.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateStandardController createStandardController = loader.getController();
        createStandardController.setStandard(standard);
        createStandardController.setUser(account);
        stage.show();
    }

    public void deleteStandard() {
        StandardDB.deleteStandard();
        pH.setText("");
        bod.setText("");
        sulfide.setText("");
        settleableSolids.setText("");
        totalDissolvedSolid.setText("");
        suspendedSoilds.setText("");
        fatOilGrease.setText("");
        totalKjeldahlNitrogen.setText("");
        deleteButton.setDisable(true);
        createButton.setDisable(false);
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

