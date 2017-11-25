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

public class PostTreatmentController implements Initializable {
    static ItemsDB ItemsDB = new ItemsDB();
    @FXML private TableView<Item> tableView;
    @FXML private Button deleteButton,editButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        tableView.setItems(ItemsDB.loadItems());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/post-treatment-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        AddPostItemController addPostItemController = loader.getController();
        addPostItemController.setTitleLabel("Create");
        addPostItemController.setCreate();
        stage.show();
    }

    public void deleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ItemsDB.deleteItem(tableView.getSelectionModel().getSelectedItem().getNo());
            tableView.setItems(ItemsDB.loadItems());
            deleteButton.setDisable(true);
            editButton.setDisable(true);
        }
    }

    public void editItem(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/post-treatment-add.fxml"));
            stage.setScene(new Scene(loader.load()));
            AddPostItemController addPostItemController = loader.getController();
            addPostItemController.setTitleLabel("Edit");
            addPostItemController.setEdit(tableView.getSelectionModel().getSelectedItem());
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
