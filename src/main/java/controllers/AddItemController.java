package controllers;

import databases.ItemsDB;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import models.DateUtilities;
import models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemController {
    Item item;
    @FXML private TextField dissolvedOxygen,celsius,volumeWater,volumeSludge;
    @FXML private CheckBox wasteWaterMachineCheckBoxLeft,wasteWaterMachineCheckBoxRight,waterPumpCheckBoxLeft,waterPumpCheckBoxRight,aeratorCheckBoxLeft,aeratorCheckBoxRight,sedimentationMachineCheckBoxLeft,sedimentationMachineCheckBoxRight;
    @FXML private Label titleLabel;
    @FXML private ChoiceBox numberMachine,round;

    public void initialize() {
        numberMachine.setItems(FXCollections.observableArrayList("1", new Separator(), "2"));
        round.setItems(FXCollections.observableArrayList("ก่อน", new Separator(), "หลัง"));
        wasteWaterMachineCheckBoxLeft.setSelected(true);
        waterPumpCheckBoxLeft.setSelected(true);
        aeratorCheckBoxLeft.setSelected(true);
        sedimentationMachineCheckBoxLeft.setSelected(true);
        EventHandler eventHandler = (EventHandler<ActionEvent>) event -> {
            if (event.getSource() instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) event.getSource();
                if (checkBox.getId().equals("wasteWaterMachineCheckBoxLeft")) {
                    wasteWaterMachineCheckBoxRight.setSelected(!wasteWaterMachineCheckBoxLeft.isSelected());
                } else if (checkBox.getId().equals("wasteWaterMachineCheckBoxRight")) {
                    wasteWaterMachineCheckBoxLeft.setSelected(!wasteWaterMachineCheckBoxRight.isSelected());
                }
                if (checkBox.getId().equals("waterPumpCheckBoxLeft")) {
                    waterPumpCheckBoxRight.setSelected(!waterPumpCheckBoxLeft.isSelected());
                } else if (checkBox.getId().equals("waterPumpCheckBoxRight")) {
                    waterPumpCheckBoxLeft.setSelected(!waterPumpCheckBoxRight.isSelected());
                }
                if (checkBox.getId().equals("aeratorCheckBoxLeft")) {
                    aeratorCheckBoxRight.setSelected(!aeratorCheckBoxLeft.isSelected());
                } else if (checkBox.getId().equals("aeratorCheckBoxRight")) {
                    aeratorCheckBoxLeft.setSelected(!aeratorCheckBoxRight.isSelected());
                }
                if (checkBox.getId().equals("sedimentationMachineCheckBoxLeft")) {
                    sedimentationMachineCheckBoxRight.setSelected(!sedimentationMachineCheckBoxLeft.isSelected());
                } else if (checkBox.getId().equals("sedimentationMachineCheckBoxRight")) {
                    sedimentationMachineCheckBoxLeft.setSelected(!sedimentationMachineCheckBoxRight.isSelected());
                }
            }
        };
        wasteWaterMachineCheckBoxLeft.setOnAction(eventHandler);
        wasteWaterMachineCheckBoxRight.setOnAction(eventHandler);
        waterPumpCheckBoxLeft.setOnAction(eventHandler);
        waterPumpCheckBoxRight.setOnAction(eventHandler);
        aeratorCheckBoxLeft.setOnAction(eventHandler);
        aeratorCheckBoxRight.setOnAction(eventHandler);
        sedimentationMachineCheckBoxLeft.setOnAction(eventHandler);
        sedimentationMachineCheckBoxRight.setOnAction(eventHandler);
    }

    public void setEditMenu(Item item){
        this.item = item;
//        dissolvedOxygen.setText(this.item.getDissolvedOxygen());
//        celsius.setText(this.item.getCelsius());
//        volumeWater.setText(this.item.getVolume());
//        volumeSludge.setText(this.item.getVolume());
    }

    public void saveItem(ActionEvent event){
        if (item == null) {
            if (numberMachine.getValue()!=null && round.getValue()!=null && !dissolvedOxygen.getText().isEmpty() && !celsius.getText().isEmpty() && !volumeWater.getText().isEmpty()) {
                ItemsDB.saveItem(DateUtilities.getDateNumber(), Integer.parseInt(numberMachine.getValue().toString()),round.getValue().toString(),Double.parseDouble(dissolvedOxygen.getText()), Double.parseDouble(celsius.getText()), Double.parseDouble(volumeWater.getText()), Double.parseDouble(volumeSludge.getText()), isCheck(wasteWaterMachineCheckBoxLeft),isCheck(waterPumpCheckBoxLeft),isCheck(aeratorCheckBoxLeft),isCheck(sedimentationMachineCheckBoxLeft));
                dissolvedOxygen.setText("");
                celsius.setText("");
                volumeWater.setText("");
                volumeSludge.setText("");
                backToManagementWindow(event);
            }
        } else {
//            this.item.setDissolvedOxygen(dissolvedOxygen.getText());
//            this.item.setCelsius(celsius.getText());
//            this.item.setVolume(volumeWater.getText());
            //this.item.s
            //ItemsDB.editItem(this.item.getNo(), DateUtilities.getDateNumber(),this.item.getDissolvedOxygen(),this.item.getCelsius(),this.item.getVolume());
            backToManagementWindow(event);
        }
    }

    public void cancelToMenu(ActionEvent event){
        this.backToManagementWindow(event);
    }

    private void backToManagementWindow(ActionEvent event){
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/management.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel.setText(titleLabel);
    }

    public boolean isCheck(CheckBox checkBoxLeft) {
        if (checkBoxLeft.isSelected()) {
            return true;
        } else {
            return false;
        }
    }
}
