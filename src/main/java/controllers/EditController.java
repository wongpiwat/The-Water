package controllers;

import models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditController {
    Item item;
    @FXML
    private MenuButton typeFoodButton;

    @FXML
    private TextField nameFood,price;



    public void setEditMenu(Item item){
        this.item = item;
        nameFood.setText(this.item.getNameFood());
        price.setText(String.valueOf(this.item.getPrice()));
    }

    public void editMenu(ActionEvent event){
        this.item.setNameFood(nameFood.getText());
        this.item.setPrice(Double.parseDouble(price.getText()));
        ManagementController.ItemDB.editDB(this.item.getId(),this.item.getNameFood(),this.item.getPrice());
        this.backToManagementWindow(event);
    }

    public void cancelToMenu(ActionEvent event){
        this.backToManagementWindow(event);
    }

    private void backToManagementWindow(ActionEvent event){
        Button cancelToMenu = (Button) event.getSource();
        Stage stage = (Stage) cancelToMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sales-management.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
