package controllers;

import databases.MenuDB;
import models.Menu;
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

public class SalesManagementController implements Initializable {

    public static MenuDB edit = new MenuDB();

    @FXML
    private TableView<Menu> tableView;
    @FXML
    private TextField textFood;
    @FXML
    private TextField textPrice;

    @FXML
    private MenuButton typeFoodButton;
    @FXML
    private MenuItem item1, item2, item3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setItems(edit.loadMenu());
    }

    public void typeFood(ActionEvent event) {
        if (event.getTarget().equals(item1)) {
            typeFoodButton.setText(item1.getText());
        } else if (event.getTarget().equals(item2)) {
            typeFoodButton.setText(item2.getText());
        } else {
            typeFoodButton.setText(item3.getText());
        }
    }

    public void addMenu() {
        if (!textFood.getText().isEmpty() && !textPrice.getText().isEmpty() && !typeFoodButton.getText().equals("Type of Food")) {
            int id = edit.getCreateID();
            String typeFood = typeFoodButton.getText();
            String nameFood = textFood.getText();
            Double price = Double.parseDouble(textPrice.getText());
            edit.saveDB(id, typeFood, nameFood, price);
            tableView.setItems(edit.loadMenu());
//            typeFoodButton.getText();
            textFood.setText("");
            textPrice.setText("");
        }
    }

    public void deleteMenu() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            edit.deleteDB(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.setItems(edit.loadMenu());
        }
    }

    public void editMenu(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit-sale.fxml"));
            stage.setScene(new Scene(loader.load()));
            EditSalesController editSalesController = loader.getController();
            editSalesController.setEditMenu(tableView.getSelectionModel().getSelectedItem());
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
