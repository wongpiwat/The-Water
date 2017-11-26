package controllers;

import databases.AccountsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;
import java.util.Optional;

public class CreateAccountController {
    private Account account;
    @FXML private TextField department,firstName,lastName,userName,password;

    public void saveAccount(ActionEvent event) throws IOException {
        if (!department.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !userName.getText().isEmpty() && !password.getText().isEmpty()) {
            Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Create Account " + firstName.getText() + " " + lastName.getText() + " ?", ButtonType.OK, ButtonType.CANCEL);
            ConfirmationAlert.setTitle("..");
            ConfirmationAlert.setHeaderText("..");
            Optional optional = ConfirmationAlert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                AccountsDB.saveAccount(AccountsDB.getAccountID(), this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText());
                this.department.setText("");
                this.firstName.setText("");
                this.lastName.setText("");
                this.userName.setText("");
                this.password.setText("");
                this.backToAccounts(event);
            }
        }
    }

    public void backToAccounts(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts.fxml"));
        stage.setScene(new Scene(loader.load(), 1080,600));
        AccountsController accountsController = loader.getController();
        accountsController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
