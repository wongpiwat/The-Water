package controllers;

import databases.StandardDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Standard;

import java.io.IOException;

public class StandardController {
    @FXML private Label pH,bod,sulfide,settleableSolids,totalDissolvedSolid,suspendedSoilds,fatOilGrease,totalKjeldahlNitrogen;
    @FXML private Button deleteButton;
    private Standard standard;

    public void initialize() {
        standard = StandardDB.loadStandard();
        if (standard != null) {
            deleteButton.setDisable(false);
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
        }

    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void editStandard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/standard-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        AddStandardController addStandardController = loader.getController();
        addStandardController.setTitleLabel("Edit");
        addStandardController.setStandard(standard);
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
    }
}

