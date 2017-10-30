package controllers;

import databases.AccountsDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;


public class AccountsController {

    static AccountsDB accountsDB = new AccountsDB();

    @FXML
    private TableView<Accounts> accountsTableView;
    @FXML
    private Button deleteButton,editButton;


    public void initialize() {
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        accountsTableView.setItems(accountsDB.loadAccounts());
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Accounts>() {
            @Override
            public void changed(ObservableValue<? extends Accounts> observable, Accounts oldValue, Accounts newValue) {
                editButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
    }

    public void addAccount(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts-add.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


    public void deleteAccount() {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            accountsDB.deleteAccountsDB(accountsTableView.getSelectionModel().getSelectedItem().getId());
            accountsTableView.setItems(accountsDB.loadAccounts());
        }
    }


    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void editAccount(ActionEvent event) throws IOException {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts-add.fxml"));
            stage.setScene(new Scene(loader.load()));
            AddAccountController addAccountController = loader.getController();
            addAccountController.setEditAccounts(accountsTableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

}
