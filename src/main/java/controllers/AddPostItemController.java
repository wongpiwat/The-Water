package controllers;

import models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPostItemController {
    Item item;
    @FXML private TextField dissolvedOxygen,celsius,volumeWater,volumeSludge;
    @FXML private Label titleLabel;

    public void initialize() {

    }

    public void setCreate() {

    }

    public void setEdit(Item item){
        this.item = item;

        dissolvedOxygen.setText(this.item.getDissolvedOxygen()+"");
        celsius.setText(this.item.getCelsius()+"");
        volumeWater.setText(this.item.getVolumeWater()+"");
        volumeSludge.setText(this.item.getVolumeSludge()+"");

    }

    public void saveItem(ActionEvent event){
        if (item == null) {
            if (!dissolvedOxygen.getText().isEmpty() && !celsius.getText().isEmpty() && !volumeWater.getText().isEmpty()) {
                //ItemsDB.saveItem(DateUtilities.getDateNumber(), Integer.parseInt(numberMachine.getValue().toString()),Integer.parseInt(round.getValue().toString()),Double.parseDouble(dissolvedOxygen.getText()), Double.parseDouble(celsius.getText()), Double.parseDouble(volumeWater.getText()), Double.parseDouble(volumeSludge.getText()), isCheck(wasteWaterMachineCheckBoxLeft),isCheck(waterPumpCheckBoxLeft),isCheck(aeratorCheckBoxLeft),isCheck(sludgeDewateringMachineCheckBoxLeft));
                dissolvedOxygen.setText("");
                celsius.setText("");
                volumeWater.setText("");
                volumeSludge.setText("");
                backToManagementWindow(event);
            }
        } else {
            item.setDissolvedOxygen(Double.parseDouble(dissolvedOxygen.getText()));
            item.setCelsius(Double.parseDouble(celsius.getText()));
            item.setVolumeWater(Double.parseDouble(volumeWater.getText()));
            item.setVolumeSludge(Double.parseDouble(volumeSludge.getText()));

            //ItemsDB.editItem(item.getNo(), DateUtilities.getDateNumber(),item.getNumberMachine(),item.getRound(),item.getDissolvedOxygen(),item.getCelsius(),item.getVolumeWater(),item.getVolumeSludge(),item.isWasteWaterMachine(),item.isWaterPump(),item.isAerator(),item.isSludgeDewateringMachine());
            backToManagementWindow(event);
        }
    }

    public void cancelToMenu(ActionEvent event){
        this.backToManagementWindow(event);
    }

    private void backToManagementWindow(ActionEvent event){
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/post-treatment.fxml"));
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

}
