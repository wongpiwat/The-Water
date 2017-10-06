package controllers;

import databases.AccountsDB;
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


public class AccountsManagementController {

    static AccountsDB accountsDB = new AccountsDB();

    @FXML
    private TableView<Accounts> accountsTableView;
    @FXML
    private TextField departmentText, firstNameText, lastNameText, usernameText, passwordText;


    public void initialize() {
        accountsTableView.setItems(accountsDB.loadAccounts());
    }

    public void addAccount() {
        if (!departmentText.getText().isEmpty() && !firstNameText.getText().isEmpty() && !lastNameText.getText().isEmpty()
                && !usernameText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
            int id = accountsDB.getCreateAccountsID();
            String department = departmentText.getText();
            String firstName = firstNameText.getText();
            String lastName = lastNameText.getText();
            String username = usernameText.getText();
            String password = passwordText.getText();
            accountsDB.saveAccountsDB(id, department, firstName, lastName, username, password);
            accountsTableView.setItems(accountsDB.loadAccounts());
            departmentText.setText("");
            firstNameText.setText("");
            lastNameText.setText("");
            usernameText.setText("");
            passwordText.setText("");
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edit-accounts.fxml"));
            stage.setScene(new Scene(loader.load()));
            EditAccountController editAccountController = loader.getController();
            editAccountController.setEditAccounts(accountsTableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

}
