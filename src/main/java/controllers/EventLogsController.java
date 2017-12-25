package controllers;

import databases.EventLogsDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Account;
import models.Log;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Optional;

public class EventLogsController {
    private Account account;
    @FXML private TableView<Log> logsTableView;

    public void initialize() {
        logsTableView.setItems(EventLogsDBConnector.getEventLogs());
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

    public void deleteAllLogsOnAction(ActionEvent event) {
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
            if (account.getPassword().equals(usernamePassword)) {
                EventLogsDBConnector.deleteAllLogs();
                EventLogsDBConnector.resetSequence();
                logsTableView.setItems(EventLogsDBConnector.getEventLogs());
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",account.getUsername(),"Password error");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Password error");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
                logsTableView.setItems(EventLogsDBConnector.getEventLogs());
            }
        });
    }

    public void setUser(Account account) {
        this.account = account;
    }

}
