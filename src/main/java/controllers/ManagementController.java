package controllers;

import databases.ItemDB;
import models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {

    public static ItemDB ItemDB = new ItemDB();

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TextField textFood;
    @FXML
    private TextField textPrice;

    @FXML
    private MenuItem item1, item2, item3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setItems(ItemDB.loadMenu());
    }

    public void addMenu() {
        if (!textFood.getText().isEmpty() && !textPrice.getText().isEmpty()) {
            int id = ItemDB.getCreateID();
            String nameFood = textFood.getText();
            Double price = Double.parseDouble(textPrice.getText());
            ItemDB.saveDB(id, nameFood, price);
            tableView.setItems(ItemDB.loadMenu());
//            typeFoodButton.getText();
            textFood.setText("");
            textPrice.setText("");
        }
    }

    public void deleteMenu() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ItemDB.deleteDB(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.setItems(ItemDB.loadMenu());
        }
    }

    public void editMenu(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit-sale.fxml"));
            stage.setScene(new Scene(loader.load()));
            EditController editController = loader.getController();
            editController.setEditMenu(tableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
