package controllers;

import databases.AccountsDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.Account;

import java.io.IOException;
import java.util.Optional;


public class AccountsController {
    private Account account;
    static AccountsDB accountsDB = new AccountsDB();
    @FXML private TableView<Account> accountsTableView;
    @FXML private Button deleteButton;

    public void initialize() {
        deleteButton.setDisable(true);
        accountsTableView.setItems(accountsDB.loadAccountsToTable());
        accountsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
                deleteButton.setDisable(false);
            }
        });
    }

    public void createAccount(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/accounts-create.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setUser(account);
        stage.show();
    }

    public void deleteAccount() {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Login");
            dialog.setHeaderText("Login Dialog");
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField username = new TextField();
            username.setPromptText("Username");
            PasswordField password = new PasswordField();
            password.setPromptText("Password");
            grid.add(new Label("Username:"), 0, 0);
            grid.add(username, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(password, 1, 1);
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            username.textProperty().addListener((usernameObservable, usernameOldValue, usernameNewValue) -> {
                loginButton.setDisable(usernameNewValue.trim().isEmpty());
            });
            dialog.getDialogPane().setContent(grid);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(username.getText(), password.getText());
                }
                return null;
            });
            Optional<Pair<String, String>> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                if (accountsTableView.getSelectionModel().getSelectedItem().getUsername().equals(usernamePassword.getKey()) && accountsTableView.getSelectionModel().getSelectedItem().getPassword().equals(usernamePassword.getValue())) {
                    Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " + accountsTableView.getSelectionModel().getSelectedItem().getFirstName() + " " + accountsTableView.getSelectionModel().getSelectedItem().getLastName() + " ?", ButtonType.OK, ButtonType.CANCEL);
                    ConfirmationAlert.setTitle("..");
                    ConfirmationAlert.setHeaderText("..");
                    Optional optional = ConfirmationAlert.showAndWait();
                    if (optional.get() == ButtonType.OK) {
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                        informationAlert.setTitle("Information Dialog");
                        informationAlert.setHeaderText("Look, an Information Dialog");
                        informationAlert.setContentText("I have a great message for you!");
                        informationAlert.showAndWait();
                        if (accountsTableView.getSelectionModel().getSelectedItem().getUsername().equals(account.getUsername()) && accountsTableView.getSelectionModel().getSelectedItem().getPassword().equals(account.getPassword())) {
                            try {
                                backToLoginOnAction();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        accountsDB.deleteAccount(accountsTableView.getSelectionModel().getSelectedItem().getId());
                        accountsTableView.setItems(accountsDB.loadAccountsToTable());
                        deleteButton.setDisable(true);
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Ooops, there was an error!");
                    errorAlert.setTitle("Error Dialog");
                    errorAlert.setHeaderText("Look, an Error Dialog");
                    errorAlert.showAndWait();
                }
            });
        }
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void backToLoginOnAction() throws IOException {
        Stage stage = (Stage) accountsTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
