package controllers;

import databases.AccountsDBConnector;
import databases.EventLogsDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Account;
import models.ErrorMessagePopup;
import utilities.CheckInput;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateAccountController implements ErrorMessagePopup {
    private boolean edit;
    private Account account, editAccount;
    private AccountsDBConnector accountsDBConnector;
    @FXML private TextField department,firstName,lastName,userName,password;
    @FXML private Label title;

    public void initialize() {
        accountsDBConnector = new AccountsDBConnector();
    }

    public void setEditAccounts(Account account){
        editAccount = account;
        department.setText(editAccount.getDepartment());
        firstName.setText(editAccount.getFirstName());
        lastName.setText(editAccount.getLastName());
        userName.setText(editAccount.getUsername());
        password.setText(editAccount.getPassword());
        edit = true;
    }

    public void saveAccountOnAction(ActionEvent event) throws IOException {
        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.OK, ButtonType.CANCEL);
        ConfirmationAlert.setTitle("The Water");
        ConfirmationAlert.setHeaderText("");
        if (edit) {
            ConfirmationAlert.setContentText("Do you want to edit account " + firstName.getText() + " " + lastName.getText() + "?");
        } else {
            ConfirmationAlert.setContentText("Do you want to create account " + firstName.getText() + " " + lastName.getText() + "?");
        }
        Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            List<Boolean> checkBoolean = new ArrayList<>();
            checkBoolean.add(CheckInput.isAllCharacter(department));
            checkBoolean.add(CheckInput.isAllCharacter(firstName));
            checkBoolean.add(CheckInput.isAllCharacter(lastName));
            List<String> checkTextField = new ArrayList<>();
            checkTextField.add(department.getText());
            checkTextField.add(firstName.getText());
            checkTextField.add(lastName.getText());
            if (CheckInput.isAllCorrectType(checkBoolean)) {
                if (edit) {
                    AccountsDBConnector.editAccount(editAccount.getType(), department.getText(), firstName.getText(), lastName.getText(), userName.getText(), password.getText());
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(), "(I) Info", account.getUsername(), "Edited " + firstName.getText() + " " + lastName.getText()+" account", "Edit Account");
                    account = AccountsDBConnector.isLogin(userName.getText(),password.getText());
                } else {
                    if (CheckInput.isCorrectUsername(accountsDBConnector.getAccounts(),userName)) {
                        AccountsDBConnector.saveAccount("Staff", department.getText(), firstName.getText(), lastName.getText(), userName.getText(), password.getText(),"Enabled");
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Created "+firstName.getText()+" "+lastName.getText()+" account","Create Account");
                    }
                }
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                this.backOnAction(event);
            } else {
                String errorMessage = getMessageError("Could not save a account");
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Could not create account","Create Account");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,errorMessage);
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        }
    }

    @Override
    public String getMessageError(String errorMessage) {
        if (!CheckInput.isAllCharacter(department)) {
            errorMessage = errorMessage+"\n Please fill alphabet in department";
        } if (!CheckInput.isAllCharacter(firstName)) {
            errorMessage = errorMessage+"\n Please fill alphabet in first name";
        } if (!CheckInput.isAllCharacter(lastName)) {
            errorMessage = errorMessage+"\n Please fill alphabet in last name";
        } if (!CheckInput.isAllCharacterNumber(userName)) {
            errorMessage = errorMessage+"\n Please fill alphabet or  in username";
        } if (!CheckInput.isAllCharacterNumber(password)) {
            errorMessage = errorMessage+"\n Please fill alphabet in password";
        }
        return errorMessage;
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountsView.fxml"));
        stage.setScene(new Scene(loader.load()));
        AccountsController accountsController = loader.getController();
        accountsController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }

    public void setTitle(String title) {
        this.title.setText(title);
        userName.setDisable(true);
    }
}
