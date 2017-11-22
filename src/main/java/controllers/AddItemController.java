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
    @FXML private CheckBox wasteWaterMachineCheckBoxLeft,wasteWaterMachineCheckBoxRight,waterPumpCheckBoxLeft,waterPumpCheckBoxRight,aeratorCheckBoxLeft,aeratorCheckBoxRight,sludgeDewateringMachineCheckBoxLeft,sludgeDewateringMachineCheckBoxRight;
    @FXML private Label titleLabel;
    @FXML private ChoiceBox numberMachine,round;

    public void initialize() {
        numberMachine.setItems(FXCollections.observableArrayList("1", new Separator(), "2"));
        round.setItems(FXCollections.observableArrayList("1", new Separator(), "2"));
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
                if (checkBox.getId().equals("sludgeDewateringMachineCheckBoxLeft")) {
                    sludgeDewateringMachineCheckBoxRight.setSelected(!sludgeDewateringMachineCheckBoxLeft.isSelected());
                } else if (checkBox.getId().equals("sludgeDewateringMachineCheckBoxRight")) {
                    sludgeDewateringMachineCheckBoxLeft.setSelected(!sludgeDewateringMachineCheckBoxRight.isSelected());
                }
            }
        };
        wasteWaterMachineCheckBoxLeft.setOnAction(eventHandler);
        wasteWaterMachineCheckBoxRight.setOnAction(eventHandler);
        waterPumpCheckBoxLeft.setOnAction(eventHandler);
        waterPumpCheckBoxRight.setOnAction(eventHandler);
        aeratorCheckBoxLeft.setOnAction(eventHandler);
        aeratorCheckBoxRight.setOnAction(eventHandler);
        sludgeDewateringMachineCheckBoxLeft.setOnAction(eventHandler);
        sludgeDewateringMachineCheckBoxRight.setOnAction(eventHandler);
    }

    public void setCreate() {
        wasteWaterMachineCheckBoxLeft.setSelected(true);
        waterPumpCheckBoxLeft.setSelected(true);
        aeratorCheckBoxLeft.setSelected(true);
        sludgeDewateringMachineCheckBoxLeft.setSelected(true);
    }

    public void setEdit(Item item){
        this.item = item;
        numberMachine.setValue(item.getNumberMachine()+"");
        round.setValue(item.getRound()+"");
        dissolvedOxygen.setText(this.item.getDissolvedOxygen()+"");
        celsius.setText(this.item.getCelsius()+"");
        volumeWater.setText(this.item.getVolumeWater()+"");
        volumeSludge.setText(this.item.getVolumeSludge()+"");

        if (this.item.isWasteWaterMachine()) {
            wasteWaterMachineCheckBoxLeft.setSelected(true);
        } else {
            wasteWaterMachineCheckBoxRight.setSelected(true);
        }
        if (this.item.isWaterPump()) {
            waterPumpCheckBoxLeft.setSelected(true);
        } else {
            waterPumpCheckBoxRight.setSelected(true);
        }
        if (this.item.isAerator()) {
            aeratorCheckBoxLeft.setSelected(true);
        } else {
            aeratorCheckBoxRight.setSelected(true);
        }
        if (this.item.isSludgeDewateringMachine()) {
            sludgeDewateringMachineCheckBoxLeft.setSelected(true);
        } else {
            sludgeDewateringMachineCheckBoxRight.setSelected(true);
        }

    }

    public void saveItem(ActionEvent event){
        if (item == null) {
            if (numberMachine.getValue()!=null && round.getValue()!=null && !dissolvedOxygen.getText().isEmpty() && !celsius.getText().isEmpty() && !volumeWater.getText().isEmpty()) {
                ItemsDB.saveItem(DateUtilities.getDateNumber(), Integer.parseInt(numberMachine.getValue().toString()),Integer.parseInt(round.getValue().toString()),Double.parseDouble(dissolvedOxygen.getText()), Double.parseDouble(celsius.getText()), Double.parseDouble(volumeWater.getText()), Double.parseDouble(volumeSludge.getText()), isCheck(wasteWaterMachineCheckBoxLeft),isCheck(waterPumpCheckBoxLeft),isCheck(aeratorCheckBoxLeft),isCheck(sludgeDewateringMachineCheckBoxLeft));
                dissolvedOxygen.setText("");
                celsius.setText("");
                volumeWater.setText("");
                volumeSludge.setText("");
                backToManagementWindow(event);
            }
        } else {
            item.setNumberMachine(Integer.parseInt(numberMachine.getValue().toString()));
            item.setRound(Integer.parseInt(round.getValue().toString()));
            item.setDissolvedOxygen(Double.parseDouble(dissolvedOxygen.getText()));
            item.setCelsius(Double.parseDouble(celsius.getText()));
            item.setVolumeWater(Double.parseDouble(volumeWater.getText()));
            item.setVolumeSludge(Double.parseDouble(volumeSludge.getText()));
            item.setWasteWaterMachine(isCheck(wasteWaterMachineCheckBoxLeft));
            item.setWaterPump(isCheck(waterPumpCheckBoxLeft));
            item.setAerator(isCheck(aeratorCheckBoxLeft));
            item.setSludgeDewateringMachine(isCheck(sludgeDewateringMachineCheckBoxLeft));
            ItemsDB.editItem(item.getNo(), DateUtilities.getDateNumber(),item.getNumberMachine(),item.getRound(),item.getDissolvedOxygen(),item.getCelsius(),item.getVolumeWater(),item.getVolumeSludge(),item.isWasteWaterMachine(),item.isWaterPump(),item.isAerator(),item.isSludgeDewateringMachine());
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
