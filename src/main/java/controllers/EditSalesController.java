package controllers;

import models.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditSalesController {
    Menu menu;
    @FXML
    private MenuButton typeFoodButton;
    @FXML
    private MenuItem item1,item2,item3;
    @FXML
    private TextField nameFood,price;

    public void typeFood(ActionEvent event) {
        if (event.getTarget().equals(item1)) {
            typeFoodButton.setText(item1.getText());
        } else if (event.getTarget().equals(item2)) {
            typeFoodButton.setText(item2.getText());
        } else {
            typeFoodButton.setText(item3.getText());
        }
    }

    public void setEditMenu(Menu menu){
        this.menu = menu;
        nameFood.setText(this.menu.getNameFood());
        price.setText(String.valueOf(this.menu.getPrice()));
        typeFoodButton.setText(this.menu.getType());
    }

    public void editMenu(ActionEvent event){
        this.menu.setNameFood(nameFood.getText());
        this.menu.setPrice(Double.parseDouble(price.getText()));
        this.menu.setType(typeFoodButton.getText());
        SalesManagementController.edit.editDB(this.menu.getId(),this.menu.getType(),this.menu.getNameFood(),this.menu.getPrice());
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
            stage.setScene(new Scene(loader.load(), 1080, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
