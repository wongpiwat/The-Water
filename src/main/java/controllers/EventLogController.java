package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Account;

import java.io.IOException;
import java.util.Optional;

public class EventLogController {
    private Account account;

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void clearLogOnAction(ActionEvent event) {
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
                Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to permanently delete all treatment ?", ButtonType.OK, ButtonType.CANCEL);
                ConfirmationAlert.setTitle("The Water");
                ConfirmationAlert.setHeaderText("");
                Optional optional = ConfirmationAlert.showAndWait();
                if (optional.get() == ButtonType.OK) {
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();

//                    preTreatmentTableView.setItems(TreatmentDBConnector.loadPreTreatmentToTable());
//                    postTreatmentTableView.setItems(TreatmentDBConnector.loadPostTreatmentToTable());
                }
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Could not login. Please try again later.");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        });
    }

    public void setUser(Account account) {
        this.account = account;
    }

}
