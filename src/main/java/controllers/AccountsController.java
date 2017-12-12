package controllers;

import databases.AccountsDBConnector;
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
import javafx.util.Pair;
import models.Account;

import java.io.IOException;
import java.util.Optional;


public class AccountsController {
    private Account account;
    static AccountsDBConnector accountsDBConnector = new AccountsDBConnector();
    @FXML private TableView<Account> accountsTableView;
    @FXML private Button deleteButton;

    public void initialize() {
        deleteButton.setDisable(true);
        accountsTableView.setItems(accountsDBConnector.loadAccountsToTable());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateAccountView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setUser(account);
        stage.show();
    }

    public void deleteAccount() {
        if (accountsTableView.getSelectionModel().getSelectedItem() != null) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("The Water");
            dialog.setHeaderText("Program needs your permission to continue");
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField username = new TextField();
            PasswordField password = new PasswordField();
            grid.add(new Label("Username:"), 0, 0);
            grid.add(username, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(password, 1, 1);

            dialog.getDialogPane().setContent(grid);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(username.getText(), password.getText());
                }
                return null;
            });
            Optional<Pair<String, String>> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                if (account.getUsername().equals(usernamePassword.getKey()) && account.getPassword().equals(usernamePassword.getValue())) {
                    Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to permanently delete " + accountsTableView.getSelectionModel().getSelectedItem().getFirstName() + " " + accountsTableView.getSelectionModel().getSelectedItem().getLastName() + " ?", ButtonType.OK, ButtonType.CANCEL);
                    ConfirmationAlert.setTitle("The Water");
                    ConfirmationAlert.setHeaderText("");
                    Optional optional = ConfirmationAlert.showAndWait();
                    if (optional.get() == ButtonType.OK) {
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
                            accountsDBConnector.deleteAccount(accountsTableView.getSelectionModel().getSelectedItem().getId());
                            accountsTableView.setItems(accountsDBConnector.loadAccountsToTable());
                            deleteButton.setDisable(true);
                        }
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not login. Please try again later.");
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
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
