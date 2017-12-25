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
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Optional;


public class AccountsController {
    private Account account;
    private AccountsDBConnector accountsDBConnector;
    @FXML private TableView<Account> accountsTableView;
    @FXML private Button deleteButton;

    public void initialize() {
        deleteButton.setDisable(true);
        accountsDBConnector = new AccountsDBConnector();
        accountsTableView.setItems(accountsDBConnector.getAccounts());
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
                deleteButton.setDisable(false);
            }
        });
    }

    public void createOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAccountView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setUser(account);
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
                if (AccountsDBConnector.checkUser(account.getUsername(),usernamePassword)) {
                    accountsDBConnector.deleteAccount(accountsTableView.getSelectionModel().getSelectedItem().getUsername());
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Deleted "+accountsTableView.getSelectionModel().getSelectedItem().getFirstName()+" "+accountsTableView.getSelectionModel().getSelectedItem().getLastName()+" account");
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                    if (accountsTableView.getSelectionModel().getSelectedItem().getUsername().equals(account.getUsername()) && accountsTableView.getSelectionModel().getSelectedItem().getPassword().equals(account.getPassword())) {
                        try {
                            backToLoginOnAction();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        accountsTableView.setItems(accountsDBConnector.getAccounts());
                        deleteButton.setDisable(true);
                    }

                } else {
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Password error: Permission denied");
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Password error: Permission denied.");
                    errorAlert.setTitle("The Water");
                    errorAlert.setHeaderText("");
                    errorAlert.showAndWait();
                }
            });
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void backToLoginOnAction() throws IOException {
        Stage stage = (Stage) accountsTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Logged out");
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
