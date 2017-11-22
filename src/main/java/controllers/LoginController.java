package controllers;
import databases.AccountsDB;
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
import models.Accounts;

import java.io.IOException;

public class LoginController {
    private AccountsDB accountsDB = new AccountsDB();
    private ObservableList<Accounts> accounts;
    @FXML private Text warningText;
    @FXML private TextField userName;
    @FXML private PasswordField userPassword;
    @FXML private Button loginBtn;

    public void initialize(){
        accounts = accountsDB.loadAccounts();
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

    public void loginOnClick(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        this.checkUsernameAndPassword(stage);
    }

    private void checkUsernameAndPassword(Stage stage) throws IOException {
        boolean loginSuccess = false;
        for (Accounts i : accounts){
            if (i.getUsername().equals(userName.getText()) && i.getPassword().equals(userPassword.getText())){
                this.loginToHome(stage);
                loginSuccess = true;
                break;
            }
        }
        if(!loginSuccess){
            warningText.setText("The username or password is incorrect.");
        }
    }

    private void loginToHome(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/home.fxml" ));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        warningText.setText("");
    }
}
