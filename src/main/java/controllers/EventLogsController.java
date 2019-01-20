package controllers;

import databases.EventLogsDBConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import utilities.AccountManager;
import utilities.DateUtilities;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventLogsController {
    private String year,month;
    @FXML private TableView<Log> logsTableView;
    @FXML private ChoiceBox yearChoiceBox,monthChoiceBox;
    @FXML private Button clearFilterButton;

    public void initialize() {
        clearFilterButton.setDisable(true);
        logsTableView.setItems(EventLogsDBConnector.getEventLogs());
        setFilter(EventLogsDBConnector.getEventLogs(),logsTableView);
    }

    private void setFilter(List<Log> logs, TableView tableView) {
        ObservableList<Log> filter = FXCollections.observableArrayList();
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> years = FXCollections.observableArrayList();
        for (int i = 0 ; i< logs.size() ; i++) {
            Date date = DateUtilities.getDate(logs.get(i).getDate());
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
                for (int index = 0 ; index < logs.size() ; index++) {
                    Date date = DateUtilities.getDate(logs.get(index).getDate());
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
                    for (int index = 0 ; index < logs.size() ; index++) {
                        Date date = DateUtilities.getDate(logs.get(index).getDate());
                        if (year.equals(DateUtilities.getYear(date)) && month.equals(DateUtilities.getMonth(date))) {
                            filter.add(logs.get(index));
                        }
                    }
                    tableView.setItems(filter);
                }
            }
        });
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene(loader.load()));
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
            if (AccountManager.getAccount().getPassword().equals(usernamePassword)) {
                EventLogsDBConnector.deleteAllLogs();
                EventLogsDBConnector.resetSequence();
                logsTableView.setItems(EventLogsDBConnector.getEventLogs());
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"Deleted");
                informationAlert.setTitle("The Water");
                informationAlert.setHeaderText("");
                informationAlert.showAndWait();
            } else {
                EventLogsDBConnector.saveLog(DateUtilities.getDateNumber(),"(E) Error",AccountManager.getAccount().getUsername(),"Password error","Event Logs");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Password error");
                errorAlert.setTitle("The Water");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
                logsTableView.setItems(EventLogsDBConnector.getEventLogs());
            }
        });
    }

    public void clearFilterOnAction() {
        setFilter(EventLogsDBConnector.getEventLogs(),logsTableView);
        logsTableView.setItems(EventLogsDBConnector.getEventLogs());
        clearFilterButton.setDisable(true);
    }
}
