package controllers;

import databases.AccountsDBConnector;
import databases.EventLogsDBConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Account;
import utilities.AccountManager;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Optional;

public class AccountsController {
    private AccountsDBConnector accountsDBConnector;
    @FXML private TableView<Account> accountsTableView;
    @FXML private Button deleteButton, blockButton, editButton;

    public void initialize() {
        deleteButton.setDisable(true);
        blockButton.setDisable(true);
        editButton.setDisable(true);
        accountsDBConnector = new AccountsDBConnector();
        accountsTableView.setItems(accountsDBConnector.getAccounts());
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
                if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
                    if (accountsTableView.getSelectionModel().getSelectedItem().getStatus().equals("Disabled")) {
                        blockButton.setText("Unblock");
                    } else {
                        blockButton.setText("Block");
                    }
                    if (accountsTableView.getSelectionModel().getSelectedItem().getActive().equals("✓")) {
                        deleteButton.setDisable(true);
                    } else {
                        deleteButton.setDisable(false);
                    }
                    if (accountsTableView.getSelectionModel().getSelectedItem().getType().equals("Supervisor")) {
                        blockButton.setDisable(true);
                        deleteButton.setDisable(true);
                        editButton.setDisable(false);
                    } else {
                        blockButton.setDisable(false);
                        editButton.setDisable(false);
                    }
                } else {
                    blockButton.setText("Block");
                }

            }
        });
    }

    public void createOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAccountView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void deleteOnAction() {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("The Water");
            dialog.setHeaderText("Program needs your permission to continue");
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            PasswordField password = new PasswordField();
            grid.add(new Label("Password:"), 0, 1);
            grid.add(password, 1, 1);
            dialog.getDialogPane().setContent(grid);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return password.getText();
                }
                return null;
            });
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                Account checkAccount = AccountsDBConnector.isLogin(AccountManager.getAccount().getUsername(),usernamePassword);
                try {
                    if (checkAccount.getUsername().equals(AccountManager.getAccount().getUsername())) {
                        accountsDBConnector.deleteAccount(accountsTableView.getSelectionModel().getSelectedItem().getUsername());
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(), "(I) Info", AccountManager.getAccount().getUsername(), "Deleted " + accountsTableView.getSelectionModel().getSelectedItem().getFirstName() + " " + accountsTableView.getSelectionModel().getSelectedItem().getLastName() + " account", "Account");
                        accountsTableView.setItems(accountsDBConnector.getAccounts());
                        deleteButton.setDisable(true);
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "Deleted");
                        informationAlert.setTitle("The Water");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                    }
                } catch (Exception e) {
                    System.err.println("Account not found");
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(), "(E) Error", AccountManager.getAccount().getUsername(), "Password error", "Account");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Password error");
                    errorAlert.setTitle("The Water");
                    errorAlert.setHeaderText("");
                    errorAlert.showAndWait();
                }
            });
        }
    }

    public void editOnAction(ActionEvent event) throws IOException {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAccountView.fxml"));
            stage.setScene(new Scene(loader.load()));
            CreateAccountController createAccountController = loader.getController();
            createAccountController.setTitle("Edit Account");
            createAccountController.setEditAccounts(accountsTableView.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

    public void blockOnAction(){
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            if (accountsTableView.getSelectionModel().getSelectedItem().getStatus().equals("Disabled")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to unblock ?", ButtonType.OK, ButtonType.CANCEL);
                alert.setTitle("The Water");
                alert.setHeaderText("");
                Optional optional = alert.showAndWait();
                if (optional.get() == ButtonType.OK) {
                    AccountsDBConnector.blockAccount(accountsTableView.getSelectionModel().getSelectedItem().getUsername(), "Enabled");
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Unblock "+accountsTableView.getSelectionModel().getSelectedItem().getFirstName()+" "+accountsTableView.getSelectionModel().getSelectedItem().getLastName()+" account","Account");
                    accountsTableView.setItems(accountsDBConnector.getAccounts());
                    blockButton.setDisable(true);
                    deleteButton.setDisable(true);
                    editButton.setDisable(true);
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "Unblocked");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to block ?", ButtonType.OK, ButtonType.CANCEL);
                alert.setTitle("The Water");
                alert.setHeaderText("");
                Optional optional = alert.showAndWait();
                if (optional.get() == ButtonType.OK) {
                    AccountsDBConnector.blockAccount(accountsTableView.getSelectionModel().getSelectedItem().getUsername(), "Disabled");
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Block "+accountsTableView.getSelectionModel().getSelectedItem().getFirstName()+" "+accountsTableView.getSelectionModel().getSelectedItem().getLastName()+" account","Account");
                    accountsTableView.setItems(accountsDBConnector.getAccounts());
                    blockButton.setDisable(true);
                    deleteButton.setDisable(true);
                    editButton.setDisable(true);
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION, "Blocked");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                }
            }
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
