package controllers;

import databases.StandardDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Standard;

import java.io.IOException;

public class AddStandardController {
    private Standard standard;
    @FXML private Label titleLabel;
    @FXML private TextField pHTextField,bodTextField,sulfideTextField,settleableSolidsTextField,totalDissolvedSolidTextField,suspendedSoildsTextField,fatOilGreaseTextField,totalKjeldahlNitrogenTextField;

    public void saveStandard(ActionEvent event) {
        if (standard == null) {
            if (!pHTextField.getText().equals("") && !bodTextField.getText().equals("") && !sulfideTextField.getText().equals("") && !settleableSolidsTextField.getText().equals("") && !totalDissolvedSolidTextField.getText().equals("") && !suspendedSoildsTextField.getText().equals("") && !fatOilGreaseTextField.getText().equals("") && !totalKjeldahlNitrogenTextField.getText().equals("")) {
                StandardDB.saveStandard(Double.parseDouble(pHTextField.getText()),Double.parseDouble(bodTextField.getText()),Double.parseDouble(sulfideTextField.getText()),Double.parseDouble(settleableSolidsTextField.getText()),Double.parseDouble(totalDissolvedSolidTextField.getText()),Double.parseDouble(suspendedSoildsTextField.getText()),Double.parseDouble(fatOilGreaseTextField.getText()),Double.parseDouble(totalKjeldahlNitrogenTextField.getText()));
                pHTextField.setText("");
                bodTextField.setText("");
                sulfideTextField.setText("");
                settleableSolidsTextField.setText("");
                totalDissolvedSolidTextField.setText("");
                suspendedSoildsTextField.setText("");
                fatOilGreaseTextField.setText("");
                totalKjeldahlNitrogenTextField.setText("");
                backToStandard(event);
            }
        } else {
            standard.setpH(Double.parseDouble(pHTextField.getText()));
            standard.setBod(Double.parseDouble(bodTextField.getText()));
            standard.setSulfide(Double.parseDouble(sulfideTextField.getText()));
            standard.setSettleableSolids(Double.parseDouble(settleableSolidsTextField.getText()));
            standard.setTotalDissolvedSolid(Double.parseDouble(totalDissolvedSolidTextField.getText()));
            standard.setSuspendedSoilds(Double.parseDouble(suspendedSoildsTextField.getText()));
            standard.setFatOilGrease(Double.parseDouble(fatOilGreaseTextField.getText()));
            standard.setTotalKjeldahlNitrogen(Double.parseDouble(totalKjeldahlNitrogenTextField.getText()));
            StandardDB.saveStandard(standard.getpH(),standard.getBod(),standard.getSulfide(),standard.getSettleableSolids(),standard.getTotalDissolvedSolid(),standard.getSuspendedSoilds(),standard.getFatOilGrease(),standard.getTotalKjeldahlNitrogen());
            backToStandard(event);
        }
    }


    public void cancelToStandard(ActionEvent event){
        this.backToStandard(event);
    }

    public void backToStandard(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/standard.fxml"));
        try{
            stage.setScene(new Scene(loader.load(), 1080,600));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setTitleLabel(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }
}
