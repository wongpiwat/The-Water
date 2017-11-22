package controllers;

import databases.ItemsDB;
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
    @FXML private CheckBox wasteWaterMachineCheckBox,waterPumpCheckBox,aeratorCheckBox,sedimentationMachineCheckBox;
    @FXML private Label titleLabel;

    public void setEditMenu(Item item){
        this.item = item;
        dissolvedOxygen.setText(this.item.getDissolvedOxygen());
        celsius.setText(this.item.getCelsius());
        volumeWater.setText(this.item.getVolume());
        volumeSludge.setText(this.item.getVolume());
    }

    public void saveItem(ActionEvent event){
        if (item == null) {
            if (!dissolvedOxygen.getText().isEmpty() && !celsius.getText().isEmpty() && !volumeWater.getText().isEmpty()) {
                ItemsDB.saveItem(DateUtilities.getDateNumber(),dissolvedOxygen.getText(), celsius.getText(), volumeWater.getText());
                dissolvedOxygen.setText("");
                celsius.setText("");
                volumeWater.setText("");
                volumeSludge.setText("");
                backToManagementWindow(event);
            }
        } else {
            this.item.setDissolvedOxygen(dissolvedOxygen.getText());
            this.item.setCelsius(celsius.getText());
            this.item.setVolume(volumeWater.getText());
            //this.item.s
            ItemsDB.editItem(this.item.getNo(), DateUtilities.getDateNumber(),this.item.getDissolvedOxygen(),this.item.getCelsius(),this.item.getVolume());
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
}
