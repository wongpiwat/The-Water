package controllers;

import databases.EventLogsDBConnector;
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
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Optional;

public class TreatmentsController {
    private Account account;
    private int deleteMode;
    @FXML private Button deleteButton;
    @FXML private TableView<Treatment> preTreatmentTableView,postTreatmentTableView, tableView;
    @FXML private Tab preTreatmentTab,postTreatmentTab,tab;

    public void initialize() {
        deleteButton.setDisable(true);
        tableView = preTreatmentTableView;
        preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
        postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
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
        deleteMode = 1;
        this.deleteAllTreatmentsOnAction();
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
                if (deleteMode == 1) {
                    if (tab.getText().equals("Pre Treatment")) {
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Deleted pre treatment");
                        TreatmentsDBConnector.deletePreTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                        preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
                        deleteButton.setDisable(true);
                        deleteMode = 0;
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                        informationAlert.setTitle("The Water");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                    } else if (tab.getText().equals("Post Treatment")) {
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Deleted post treatment");
                        TreatmentsDBConnector.deletePostTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                        postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
                        deleteButton.setDisable(true);
                        deleteMode = 0;
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                        informationAlert.setTitle("The Water");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                    }
                } else {
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",account.getUsername(),"Deleted all treatments");
                    TreatmentsDBConnector.deleteAllTreatments();
                    TreatmentsDBConnector.resetSequence();
                    preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
                    postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
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
