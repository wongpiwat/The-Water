package controllers;

import databases.ItemsDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    public static ItemsDB ItemsDB = new ItemsDB();

    @FXML private TableView<Item> tableView;
    @FXML private Button deleteButton,editButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        tableView.setItems(ItemsDB.loadDB());
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    public void createItem(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/management-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ItemsDB.deleteDB(tableView.getSelectionModel().getSelectedItem().getNo());
            tableView.setItems(ItemsDB.loadDB());
        }
    }

    public void editItem(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/management-add.fxml"));
            stage.setScene(new Scene(loader.load()));
            AddDataController addDataController = loader.getController();
            addDataController.setEditMenu(tableView.getSelectionModel().getSelectedItem());
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
