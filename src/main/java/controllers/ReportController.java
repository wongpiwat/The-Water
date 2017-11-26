package controllers;

import Utilities.DateUtilities;
import databases.PostTreatmentDatabase;
import databases.PreTreatmentDatabase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Account;
import models.Treatment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ReportController {
    private Account account;
    private List<Treatment> preTreatments = PreTreatmentDatabase.getAllPreTreatment();
    private String year,month;
    @FXML private TableView preReportTableView,postReportTableView;
    @FXML private ChoiceBox yearChoiceBox,monthChoiceBox;

    public void initialize() {
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> years = FXCollections.observableArrayList();
        for (int i = 0 ; i< preTreatments.size() ; i++) {
            Date date = DateUtilities.getDateForm(preTreatments.get(i).getDate());
            if (!years.contains(DateUtilities.getYear(date))) {
                years.add(DateUtilities.getYear(date));
            }
        }
        yearChoiceBox.setItems(years);
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue yearObservable, Object yearOldValue, Object yearNewValue) {
                months.clear();
                for (int index = 0 ; index< preTreatments.size() ; index++) {
                    int i = index;
                    Date date = DateUtilities.getDateForm(preTreatments.get(index).getDate());
                    if (yearNewValue.equals(DateUtilities.getYear(date))) {
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
                if (year != null && monthNewValue != null) {
                    month = monthNewValue.toString();
                    //System.out.println(year+" : "+month);
                    for (int index = 0;index < preTreatments.size() ; index++) {
                        Date date = DateUtilities.getDateForm(preTreatments.get(index).getDate());
                        if (year.equals(DateUtilities.getYear(date)) && month.equals(DateUtilities.getMonth(date))) {
                            System.out.println(preTreatments.get(index).getDate());
                        }
                    }
                }
            }
        });
    }

    public void backOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        stage.setScene(new Scene(loader.load()));
        HomeController homeController = loader.getController();
        homeController.setUser(account);
        stage.show();
    }

    public void setUser(Account account) {
        this.account = account;
    }

}
