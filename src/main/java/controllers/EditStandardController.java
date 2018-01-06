package controllers;

import databases.EventLogsDBConnector;
import databases.StandardDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Account;
import models.Standard;
import utilities.CheckInput;
import utilities.DateUtilities;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditStandardController {
    private Account account;
    private Standard standard;
    @FXML private TextField no,temperature,pH,dissolvedOxygen,mlss;
    @FXML private DatePicker datePicker;

    public void initialize(){
        datePicker.setValue(LocalDate.now());
    }

    public void saveOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("The Water");
        alert.setHeaderText("");
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            List<Boolean> checkBoolean = new ArrayList<>();
            checkBoolean.add(CheckInput.isAllNumber(no));
            checkBoolean.add(CheckInput.isCorrectDate(datePicker));
            checkBoolean.add(CheckInput.isAllNumber(temperature));
            checkBoolean.add(CheckInput.isAllNumber(pH));
            checkBoolean.add(CheckInput.isAllNumber(dissolvedOxygen));
            checkBoolean.add(CheckInput.isAllNumber(mlss));
            checkBoolean.add(CheckInput.isCorrectTemp(temperature));
            checkBoolean.add(CheckInput.isCorrectPH(pH));
            List<String> checkTextField = new ArrayList<>();
            checkTextField.add(no.getText());
            checkTextField.add(temperature.getText());
            checkTextField.add(pH.getText());
            checkTextField.add(dissolvedOxygen.getText());
            checkTextField.add(mlss.getText());
            if (CheckInput.isAllCorrectType(checkBoolean)) {
                StandardDBConnector.saveStandard(Integer.parseInt(no.getText()),DateUtilities.getFormDatePicker(datePicker.getValue()),Double.parseDouble(temperature.getText()), Double.parseDouble(pH.getText()), Double.parseDouble(dissolvedOxygen.getText()), Double.parseDouble(mlss.getText()),account.getUsername(),DateUtilities.getDateNumber());
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Saved standard","Create Standard");
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                backToStandard(event);
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Could not save standard","Create Standard");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not save standard");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        }
    }


    public void backOnAction(ActionEvent event) throws IOException {
        this.backToStandard(event);
    }

    public void backToStandard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        StandardController standardController = loader.getController();
        standardController.setUser(account);
        stage.show();
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
        if (standard != null) {
            this.temperature.setText(standard.getTemperature()+"");
            this.pH.setText(standard.getpH()+"");
            this.dissolvedOxygen.setText(standard.getDissolvedOxygen()+"");
            this.mlss.setText(standard.getMlss()+"");
        }
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
