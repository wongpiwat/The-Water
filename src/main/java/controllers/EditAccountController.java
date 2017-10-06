package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Accounts;

import java.io.IOException;

public class EditAccountController {

    Accounts accounts;

    @FXML TextField department,firstname,lastname,username,password;

    public void setEditAccounts(Accounts accounts){
        this.accounts = accounts;
        department.setText(this.accounts.getDepartment());
        firstname.setText(this.accounts.getFirstname());
        lastname.setText(this.accounts.getLastname());
        username.setText(this.accounts.getUsername());
        password.setText(this.accounts.getPassword());
    }
    public void editAccount(ActionEvent event){
        this.accounts.setDepartment(department.getText());
        this.accounts.setFirstname(firstname.getText());
        this.accounts.setLastname(lastname.getText());
        this.accounts.setUsername(username.getText());
        this.accounts.setPassword(password.getText());
        AccountsManagementController.accountsDB.editAccountsDB(this.accounts.getId(),this.accounts.getDepartment(),this.accounts.getFirstname(),this.accounts.getLastname(),this.accounts.getUsername(),this.accounts.getPassword());
        this.backToAccounts(event);
    }

    public void cancelToAccounts(ActionEvent event){
        this.backToAccounts(event);
    }

    public void backToAccounts(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts-management.fxml"));
        try{
            stage.setScene(new Scene(loader.load(), 1080,600));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
