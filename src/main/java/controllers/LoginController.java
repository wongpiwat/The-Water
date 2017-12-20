package controllers;
import databases.AccountsDBConnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;

public class LoginController {
    private AccountsDBConnector accountsDBConnector = new AccountsDBConnector();
    //private ObservableList<Account> accounts;
    @FXML private Text warningText;
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;

    public void initialize() {
        //accounts = accountsDBConnector.loadAccountsToTable();
        userName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    TextField userName  = (TextField) event.getSource();
                    Stage stage = (Stage) userName.getScene().getWindow();
                    try {
                        checkUsernameAndPassword(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        userPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    PasswordField passwordField  = (PasswordField) event.getSource();
                    Stage stage = (Stage) passwordField.getScene().getWindow();
                    try {
                        checkUsernameAndPassword(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void loginOnAction(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.checkUsernameAndPassword(stage);
    }

    private void checkUsernameAndPassword(Stage stage) throws IOException {
        boolean loginSuccess = false;
        userName.getText();
        userPassword.getText();
        Account account = AccountsDBConnector.isLogin(userName.getText(),userPassword.getText());
        if (account!=null) {
            loginSuccess = true;
            this.loginToHome(stage,account);
        }
        if(!loginSuccess){
            warningText.setText("The username or password is incorrect.");
        }
    }

    private void loginToHome(Stage stage,Account account) throws IOException {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
        warningText.setText("");
    }
}
