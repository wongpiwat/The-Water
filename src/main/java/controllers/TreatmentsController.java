package controllers;

import databases.EventLogsDBConnector;
import databases.TreatmentsDBConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import utilities.AccountManager;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TreatmentsController {
    private int deleteMode;
    private String year,month;
    @FXML private Tab preTreatmentTab,postTreatmentTab,tab;
    @FXML private TableView<Treatment> preTreatmentTableView,postTreatmentTableView, tableView;
    @FXML private ChoiceBox yearChoiceBox,monthChoiceBox;
    @FXML private Button deleteButton, deleteAllButton, clearFilterButton, createButton;

    public void initialize() {
        if (AccountManager.getAccount().getType().equals("Supervisor")) {
            createButton.setDisable(true);
            deleteButton.setDisable(true);
        } else {
            deleteAllButton.setDisable(true);
        }
        deleteButton.setDisable(true);
        clearFilterButton.setDisable(true);
        tableView = preTreatmentTableView;
        setFilter(TreatmentsDBConnector.getPreTreatments(),preTreatmentTableView);
        preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
        postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
        preTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                if (newValue == null) {
                    deleteButton.setDisable(true);
                } else {
                    if (AccountManager.getAccount().getType().equals("Staff")) {
                        deleteButton.setDisable(false);
                    }
                }
            }
        });
        postTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                if (newValue == null) {
                    deleteButton.setDisable(true);
                } else {
                    if (AccountManager.getAccount().getType().equals("Staff")) {
                        deleteButton.setDisable(false);
                    }
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
                    setFilter(TreatmentsDBConnector.getPreTreatments(),preTreatmentTableView);
                    preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
                    clearFilterButton.setDisable(true);
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
                    setFilter(TreatmentsDBConnector.getPostTreatments(),postTreatmentTableView);
                    postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
                    clearFilterButton.setDisable(true);
                }
            }
        });
    }

    private void setFilter(List<Treatment> treatments, TableView tableView) {
        ObservableList<Treatment> filter = FXCollections.observableArrayList();
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> years = FXCollections.observableArrayList();
        for (int i = 0 ; i< treatments.size() ; i++) {
            Date date = DateUtilities.getDate(treatments.get(i).getDateWater());
            if (!years.contains(DateUtilities.getYear(date))) {
                years.add(DateUtilities.getYear(date));
            }
        }
        yearChoiceBox.setItems(years);
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue yearObservable, Object yearOldValue, Object yearNewValue) {
                clearFilterButton.setDisable(false);
                months.clear();
                for (int index = 0 ; index < treatments.size() ; index++) {
                    Date date = DateUtilities.getDate(treatments.get(index).getDateWater());
                    if (yearNewValue != null && yearNewValue.equals(DateUtilities.getYear(date))) {
                        if (!months.contains(DateUtilities.getMonth(date))) {
                            months.add(DateUtilities.getMonth(date));
                            year = yearNewValue.toString();
                        }
                    }
                    monthChoiceBox.setItems(months);
                }
            }
        });
        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue monthObservable, Object monthOldValue, Object monthNewValue) {
                clearFilterButton.setDisable(false);
                filter.clear();
                tableView.getItems().clear();
                if (year != null && monthNewValue != null) {
                    month = monthNewValue.toString();
                    for (int index = 0 ; index < treatments.size() ; index++) {
                        Date date = DateUtilities.getDate(treatments.get(index).getDateWater());
                        if (year.equals(DateUtilities.getYear(date)) && month.equals(DateUtilities.getMonth(date))) {
                            filter.add(treatments.get(index));
                        }
                    }
                    tableView.setItems(filter);
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
        stage.show();
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
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
            if (AccountManager.getAccount().getPassword().equals(usernamePassword)) {
                if (deleteMode == 1) {
                    if (tab.getText().equals("Pre Treatment")) {
                        TreatmentsDBConnector.deletePreTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Deleted pre treatment","Treatment");
                        preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
                        deleteButton.setDisable(true);
                        deleteMode = 0;
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                        informationAlert.setTitle("The Water");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                    } else if (tab.getText().equals("Post Treatment")) {
                        TreatmentsDBConnector.deletePostTreatment(tableView.getSelectionModel().getSelectedItem().getId());
                        EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Deleted post treatment","Treatment");
                        postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
                        deleteButton.setDisable(true);
                        deleteMode = 0;
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                        informationAlert.setTitle("The Water");
                        informationAlert.setHeaderText("");
                        informationAlert.showAndWait();
                    }
                } else {
                    TreatmentsDBConnector.deleteAllTreatments();
                    TreatmentsDBConnector.resetSequence();
                    EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(I) Info",AccountManager.getAccount().getUsername(),"Deleted all treatments","Treatment");
                    preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
                    postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                    informationAlert.setTitle("The Water");
                    informationAlert.setHeaderText("");
                    informationAlert.showAndWait();
                }
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",AccountManager.getAccount().getUsername(),"Password error","Treatment");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Password error");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }
        });
    }

    public void clearFilterOnAction() {
        if (preTreatmentTab.isSelected()) {
            setFilter(TreatmentsDBConnector.getPreTreatments(),preTreatmentTableView);
            preTreatmentTableView.setItems(TreatmentsDBConnector.getPreTreatments());
            clearFilterButton.setDisable(true);
        } else {
            setFilter(TreatmentsDBConnector.getPostTreatments(),postTreatmentTableView);
            postTreatmentTableView.setItems(TreatmentsDBConnector.getPostTreatments());
            clearFilterButton.setDisable(true);
        }
    }
}
