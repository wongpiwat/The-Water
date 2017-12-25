package controllers;

import databases.AccountsDBConnector;
import databases.EventLogsDBConnector;
import javafx.collections.FXCollections;
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
    private Account account;
    @FXML private TextField department,firstName,lastName,userName,password;
    @FXML private ChoiceBox accountTypeChoiceBox;

    public void initialize() {
        accountTypeChoiceBox.setItems(FXCollections.observableArrayList("Staff",new Separator(), "Supervisor"));
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
            if (accountTypeChoiceBox.getSelectionModel().getSelectedItem()!=null && CheckInput.isAllCorrectEmpty(checkTextField) && CheckInput.isAllCorrectType(checkBoolean)) {
                AccountsDBConnector.saveAccount(accountTypeChoiceBox.getSelectionModel().getSelectedItem().toString(), this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText());
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Created "+firstName.getText()+" "+lastName.getText()+" Account");
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Created");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                this.department.setText("");
                this.firstName.setText("");
                this.lastName.setText("");
                this.userName.setText("");
                this.password.setText("");
                this.backOnAction(event);
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Could not create account");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not create account. Please fill out these fields and click save changes.");
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
        stage.setScene(new Scene(loader.load(), 1080,600));
        AccountsController accountsController = loader.getController();
        accountsController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
