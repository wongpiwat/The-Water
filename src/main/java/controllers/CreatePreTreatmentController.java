package controllers;

import databases.EventLogsDBConnector;
import databases.TreatmentsDBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import models.ErrorMessagePopup;
import utilities.AccountManager;
import utilities.CheckInput;
import utilities.DateUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreatePreTreatmentController implements ErrorMessagePopup {
    private List<String> itemHour = new  ArrayList<String>(){{add("00");add("01");add("02");add("03");add("04");add("05");add("06");add("07");add("08");add("09");add("10");add("11");add("12");add("13");add("14");add("15");add("16");add("17");add("18");add("19");add("20");add("21");add("22");add("23"); }};
    private List<String> itemMinute = new ArrayList<String>(){{add("00");add("01");add("02");add("03");add("04");add("05");add("06");add("07");add("08");add("09");add("10");add("11");add("12");add("13");add("14");add("15");add("16");add("17");add("18");add("19");add("20");add("21");add("22");add("23");add("24");add("25");add("26");add("27");add("28");add("29");add("30");add("31");add("32");add("33");add("34");add("35");add("36");add("37");add("38");add("39");add("40");add("41");add("42");add("43");add("44");add("45");add("46");add("47");add("48");add("49");add("50");add("51");add("52");add("53");add("54");add("55");add("56");add("57");add("58");add("59");}};
    @FXML private TextField volumeWater,temperature,pH,dissolvedOxygen,mlss;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox hourComboBox;
    @FXML private ComboBox minuteComboBox;

    public void initialize(){
        ObservableList<String> itemH = FXCollections.observableArrayList();
        itemH.addAll(itemHour);
        ObservableList<String> itemM = FXCollections.observableArrayList();
        itemM.addAll(itemMinute);
        datePicker.setValue(LocalDate.now());
        hourComboBox.setValue("00");
        hourComboBox.setItems(itemH);
        minuteComboBox.setValue("00");
        minuteComboBox.setItems(itemM);
    }

    public void saveOnAction(ActionEvent event) throws IOException {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save ?", ButtonType.OK, ButtonType.CANCEL);
        ConfirmationAlert.setTitle("The Water");
        ConfirmationAlert.setHeaderText("");
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            List<Boolean> checkBoolean = new ArrayList<>();
            checkBoolean.add(CheckInput.isAllNumber(volumeWater));
            checkBoolean.add(CheckInput.isAllNumber(temperature));
            checkBoolean.add(CheckInput.isAllNumber(pH));
            checkBoolean.add(CheckInput.isAllNumber(dissolvedOxygen));
            checkBoolean.add(CheckInput.isAllNumber(mlss));
            checkBoolean.add(CheckInput.isCorrectDate(datePicker));
            checkBoolean.add(CheckInput.isCorrectTime(datePicker,hourComboBox,minuteComboBox));
            checkBoolean.add(CheckInput.isCorrectWater(volumeWater));
            checkBoolean.add(CheckInput.isCorrectTemp(temperature));
            checkBoolean.add(CheckInput.isCorrectPH(pH));
            checkBoolean.add(CheckInput.isCorrectDO(dissolvedOxygen));
            checkBoolean.add(CheckInput.isCorrectMLSS(mlss));
            if (CheckInput.isAllCorrectType(checkBoolean)) {
                String dateWater = String.format("%s %s:%s",DateUtilities.getFormDatePicker(datePicker.getValue()),hourComboBox.getValue(),minuteComboBox.getValue());
                double volumeWaterValue = Double.parseDouble(volumeWater.getText());
                double temperatureValue = Double.parseDouble(temperature.getText());
                double pHValue = Double.parseDouble(pH.getText());
                double dissolvedOxygenValue = Double.parseDouble(dissolvedOxygen.getText());
                double mlssValue = Double.parseDouble(mlss.getText());
                TreatmentsDBConnector.savePreTreatment(dateWater,volumeWaterValue, temperatureValue, pHValue, dissolvedOxygenValue,mlssValue,DateUtilities.getDateNumber(),AccountManager.getAccount().getUsername());
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info", AccountManager.getAccount().getUsername(),"Saved pre treatment","Create Pre Treatment");
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                backToTreatmentOnAction(event);
            } else {
                String errorMessage = getMessageError("Could not save a pre treatment");
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",AccountManager.getAccount().getUsername(),"Could not save pre treatment","Create Pre Treatment");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,errorMessage);
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseTreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void backToTreatmentOnAction(ActionEvent event) throws IOException {
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TreatmentsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @Override
    public String getMessageError(String errorMessage) {
        if (!CheckInput.isCorrectDate(datePicker)) {
            errorMessage = errorMessage+"\n Date error";
        } if (!CheckInput.isCorrectTime(datePicker,hourComboBox,minuteComboBox)) {
            errorMessage = errorMessage+"\n Time error";
        } if (!CheckInput.isAllNumber(volumeWater)) {
            errorMessage = errorMessage+"\n Please fill number in Volume Water";
        } else {
            if (!CheckInput.isCorrectWater(volumeWater)) {
                errorMessage = errorMessage+"\n Volume Water error";
            }
        }  if (!CheckInput.isAllNumber(temperature)) {
            errorMessage = errorMessage+"\n Please fill number in Temperature";
        } else {
            if (!CheckInput.isCorrectTemp(temperature)) {
                errorMessage = errorMessage+"\n Temperature error";
            }
        }  if (!CheckInput.isAllNumber(pH)) {
            errorMessage = errorMessage+"\n Please fill number in pH";
        } else {
            if (!CheckInput.isCorrectPH(pH)) {
                errorMessage = errorMessage+"\n pH error";
            }
        } if (!CheckInput.isAllNumber(dissolvedOxygen)) {
            errorMessage = errorMessage+"\n Please fill number in Dissolved Oxygen";
        } else {
            if (!CheckInput.isCorrectDO(dissolvedOxygen)) {
                errorMessage = errorMessage+"\n Dissolved Oxygen error";
            }
        } if (!CheckInput.isAllNumber(mlss)) {
            errorMessage = errorMessage+"\n Please fill number in MLSS";
        } else {
            if (!CheckInput.isCorrectMLSS(mlss)) {
                errorMessage = errorMessage+"\n MLSS error";
            }
        }
        return errorMessage;
    }
}
