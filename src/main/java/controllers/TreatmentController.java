package controllers;

import databases.TreatmentDBConnector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import models.Account;
import models.Treatment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class TreatmentController {
    private Account account;
    @FXML private Button deleteButton;
    @FXML private TableView<Treatment> preTreatmentTableView,postTreatmentTableView, tableView;
    @FXML private Tab preTreatmentTab,postTreatmentTab,tab;

    public void initialize() {
        tableView = preTreatmentTableView;
        tab = preTreatmentTab;
        preTreatmentTableView.setItems(TreatmentDBConnector.loadPreTreatmentToTable());
        postTreatmentTableView.setItems(TreatmentDBConnector.loadPostTreatmentToTable());
        preTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (preTreatmentTab.isSelected()) {
                    postTreatmentTableView.getSelectionModel().select(null);
                    tab = preTreatmentTab;
                    tableView = preTreatmentTableView;
                }
            }
        });
        postTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (postTreatmentTab.isSelected()) {
                    preTreatmentTableView.getSelectionModel().select(null);
                    tab = postTreatmentTab;
                    tableView = postTreatmentTableView;
                }
            }
        });
    }

    public void deleteOnAction() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete No." + tableView.getSelectionModel().getSelectedItem().getId() + " ?", ButtonType.OK, ButtonType.CANCEL);
            alert.setTitle("The Water");
            alert.setHeaderText("");
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK && tab.getText().equals("Pre Treatment")) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                TreatmentDBConnector.deletePreTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                preTreatmentTableView.setItems(TreatmentDBConnector.loadPreTreatmentToTable());
                deleteButton.setDisable(true);
            } else if (optional.get() == ButtonType.OK && tab.getText().equals("Post Treatment")) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                TreatmentDBConnector.deletePostTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                postTreatmentTableView.setItems(TreatmentDBConnector.loadPostTreatmentToTable());
                deleteButton.setDisable(true);
            }
        }
    }

    public void createItem(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseTreatmentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        ChooseTreatmentController chooseTreatmentController = loader.getController();
        chooseTreatmentController.setUser(account);
        stage.show();
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

    public void setUser(Account account) {
        this.account = account;
    }

    public void deleteAllTreatmentOnAction() {
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
                Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to permanently delete all treatment ?", ButtonType.OK, ButtonType.CANCEL);
                ConfirmationAlert.setTitle("The Water");
                ConfirmationAlert.setHeaderText("");
                Optional optional = ConfirmationAlert.showAndWait();
                if (optional.get() == ButtonType.OK) {
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                    TreatmentDBConnector.deleteAllTreatment();
                    TreatmentDBConnector.resetSequence();
                    preTreatmentTableView.setItems(TreatmentDBConnector.loadPreTreatmentToTable());
                    postTreatmentTableView.setItems(TreatmentDBConnector.loadPostTreatmentToTable());
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
