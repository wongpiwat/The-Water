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
import utilities.CheckInput;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateAccountController {
    private boolean edit;
    private Account account;
    private AccountsDBConnector accountsDBConnector;
    @FXML private TextField department,firstName,lastName,userName,password;
    @FXML private Label title;

    public void initialize() {
        accountsDBConnector = new AccountsDBConnector();
    }

    public void setEditAccounts(Account account){
        this.account = account;
        department.setText(this.account.getDepartment());
        firstName.setText(this.account.getFirstName());
        lastName.setText(this.account.getLastName());
        userName.setText(this.account.getUsername());
        password.setText(this.account.getPassword());
        edit = true;
    }

    public void saveAccountOnAction(ActionEvent event) throws IOException {
            Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Create Account " + firstName.getText() + " " + lastName.getText() + " ?", ButtonType.OK, ButtonType.CANCEL);
            ConfirmationAlert.setTitle("The Water");
            ConfirmationAlert.setHeaderText("");
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
            if (CheckInput.isAllCorrectEmpty(checkTextField) && CheckInput.isAllCorrectType(checkBoolean)) {
                if (edit) {
                    AccountsDBConnector.editAccount("Staff", this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText());
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(), "(I) Info", account.getUsername(), "Edited " + firstName.getText() + " " + lastName.getText() + " Account", "Edit Account");
                } else {
                    if (CheckInput.isCorrectUsername(accountsDBConnector.getAccounts(),userName)) {
                        AccountsDBConnector.saveAccount("Staff",this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText(),"Enabled");
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Created "+firstName.getText()+" "+lastName.getText()+" Account","Create Account");
                    }
                }
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Saved");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                this.backOnAction(event);
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Could not create account","Create Account");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not create account");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        }
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
