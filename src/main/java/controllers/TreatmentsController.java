package controllers;

import databases.TreatmentsDBConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
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

public class TreatmentsController {
    private Account account;
    @FXML private Button deleteButton;
    @FXML private TableView<Treatment> preTreatmentTableView,postTreatmentTableView, tableView;
    @FXML private Tab preTreatmentTab,postTreatmentTab,tab;

    public void initialize() {
        deleteButton.setDisable(true);
        tableView = preTreatmentTableView;
        preTreatmentTableView.setItems(TreatmentsDBConnector.loadPreTreatmentToTable());
        postTreatmentTableView.setItems(TreatmentsDBConnector.loadPostTreatmentToTable());
        preTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                if (newValue == null) {
                    deleteButton.setDisable(true);
                } else {
                    deleteButton.setDisable(false);
                }
            }
        });
        postTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                if (newValue == null) {
                    deleteButton.setDisable(true);
                } else {
                    deleteButton.setDisable(false);
                }
            }
        });
        tab = preTreatmentTab;
        preTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (preTreatmentTab.isSelected()) {
                    deleteButton.setDisable(true);
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
                    deleteButton.setDisable(true);
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
                TreatmentsDBConnector.deletePreTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                preTreatmentTableView.setItems(TreatmentsDBConnector.loadPreTreatmentToTable());
                deleteButton.setDisable(true);
            } else if (optional.get() == ButtonType.OK && tab.getText().equals("Post Treatment")) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
                TreatmentsDBConnector.deletePostTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                postTreatmentTableView.setItems(TreatmentsDBConnector.loadPostTreatmentToTable());
                deleteButton.setDisable(true);
            }
        }
    }

    public void createOnAction(ActionEvent event) throws IOException {
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

    public void deleteAllTreatmentsOnAction() {
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
                    TreatmentsDBConnector.deleteAllTreatment();
                    TreatmentsDBConnector.resetSequence();
                    preTreatmentTableView.setItems(TreatmentsDBConnector.loadPreTreatmentToTable());
                    postTreatmentTableView.setItems(TreatmentsDBConnector.loadPostTreatmentToTable());
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
