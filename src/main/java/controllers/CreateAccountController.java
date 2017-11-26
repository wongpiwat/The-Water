package controllers;

import databases.AccountsDB;
import javafx.collections.FXCollections;
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
    @FXML private ChoiceBox accountTypeChoiceBox;

    public void initialize() {
        accountTypeChoiceBox.setItems(FXCollections.observableArrayList("Administrator",new Separator(), "User"));
    }

    public void saveAccount(ActionEvent event) throws IOException {
            Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Create Account " + firstName.getText() + " " + lastName.getText() + " ?", ButtonType.OK, ButtonType.CANCEL);
            ConfirmationAlert.setTitle("..");
            ConfirmationAlert.setHeaderText("..");
            Optional optional = ConfirmationAlert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            if (accountTypeChoiceBox.getSelectionModel().getSelectedItem()!=null && !department.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !userName.getText().isEmpty() && !password.getText().isEmpty()) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Information Dialog");
                informationAlert.setHeaderText("Look, an Information Dialog");
                informationAlert.setContentText("I have a great message for you!");
                informationAlert.showAndWait();
                AccountsDB.saveAccount(AccountsDB.getAccountID(), accountTypeChoiceBox.getSelectionModel().getSelectedItem().toString(), this.department.getText(), this.firstName.getText(), this.lastName.getText(), this.userName.getText(), this.password.getText());
                this.department.setText("");
                this.firstName.setText("");
                this.lastName.setText("");
                this.userName.setText("");
                this.password.setText("");
                this.backToAccounts(event);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Ooops, there was an error!");
                errorAlert.setTitle("Error Dialog");
                errorAlert.setHeaderText("Look, an Error Dialog");
                errorAlert.showAndWait();
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
