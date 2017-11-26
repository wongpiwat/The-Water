package controllers;

import databases.TreatmentDBConnector;
import models.Report;
import utilities.CalculateUtilities;
import utilities.DateUtilities;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportController {
    private Account account;
    private List<Treatment> preTreatments = TreatmentDBConnector.getAllPreTreatment();
    //private List<Treatment> postTreatments = new ArrayList<>();
    private List<Treatment> allTreatments = new ArrayList<>();
    private List<Treatment> oneWeekTreatments = new ArrayList<>();
    private List<Treatment> twoWeekTreatments = new ArrayList<>();
    private List<Treatment> threeWeekTreatments = new ArrayList<>();
    private List<Treatment> fourWeekTreatments = new ArrayList<>();
    private List<Treatment> fiveWeekTreatments = new ArrayList<>();
    ObservableList<Report> preReport = FXCollections.observableArrayList();
    ObservableList<Report> clearReport = FXCollections.observableArrayList();
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

                //allTreatments.clear();
                preReport.clear();
                preReportTableView.getItems().clear();
                preReportTableView.setItems(clearReport);
                for (int index = 0 ; index< preTreatments.size() ; index++) {
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
                //allTreatments.clear();
                //preReport.clear();
                //preReportTableView.getItems().clear();
                //preReportTableView.setItems(clearReport);
                if (year != null && monthNewValue != null) {
                    month = monthNewValue.toString();
                    for (int index = 0;index < preTreatments.size() ; index++) {
                        Date date = DateUtilities.getDateForm(preTreatments.get(index).getDate());
                        if (year.equals(DateUtilities.getYear(date)) && month.equals(DateUtilities.getMonth(date))) {
                            System.out.println(preTreatments.get(index).getDate() + " Date");
                            if (DateUtilities.getWeek(DateUtilities.getDateForm(preTreatments.get(index).getDate())) == 1)  {
                                oneWeekTreatments.add(preTreatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDateForm(preTreatments.get(index).getDate())) == 2)  {
                                twoWeekTreatments.add(preTreatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDateForm(preTreatments.get(index).getDate())) == 3)  {
                                threeWeekTreatments.add(preTreatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDateForm(preTreatments.get(index).getDate())) == 4)  {
                                fourWeekTreatments.add(preTreatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDateForm(preTreatments.get(index).getDate())) == 5)  {
                                fiveWeekTreatments.add(preTreatments.get(index));
                            }
                            allTreatments.add(preTreatments.get(index));
                        }
                    }
                    preReport.add(new Report(1,CalculateUtilities.getAverageVolumeWater(oneWeekTreatments),CalculateUtilities.getAverageTemperature(oneWeekTreatments),CalculateUtilities.getAveragePH(oneWeekTreatments),CalculateUtilities.getAverageMLSS(oneWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(oneWeekTreatments)));
                    preReport.add(new Report(2,CalculateUtilities.getAverageVolumeWater(twoWeekTreatments),CalculateUtilities.getAverageTemperature(twoWeekTreatments),CalculateUtilities.getAveragePH(twoWeekTreatments),CalculateUtilities.getAverageMLSS(twoWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(twoWeekTreatments)));
                    preReport.add(new Report(3,CalculateUtilities.getAverageVolumeWater(threeWeekTreatments),CalculateUtilities.getAverageTemperature(threeWeekTreatments),CalculateUtilities.getAveragePH(threeWeekTreatments),CalculateUtilities.getAverageMLSS(threeWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(threeWeekTreatments)));
                    preReport.add(new Report(4,CalculateUtilities.getAverageVolumeWater(fourWeekTreatments),CalculateUtilities.getAverageTemperature(fourWeekTreatments),CalculateUtilities.getAveragePH(fourWeekTreatments),CalculateUtilities.getAverageMLSS(fourWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(fourWeekTreatments)));
                    preReport.add(new Report(5,CalculateUtilities.getAverageVolumeWater(fiveWeekTreatments),CalculateUtilities.getAverageTemperature(fiveWeekTreatments),CalculateUtilities.getAveragePH(fiveWeekTreatments),CalculateUtilities.getAverageMLSS(fiveWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(fiveWeekTreatments)));
                    preReportTableView.setItems(preReport);

//                    CalculateUtilities.getAverageVolumeWater(allTreatments);
//                    CalculateUtilities.getAverageTemperature(allTreatments);
//                    CalculateUtilities.getAveragePH(allTreatments);
//                    CalculateUtilities.getAverageMLSS(allTreatments);
//                    CalculateUtilities.getAverageDissolvedOxygen(allTreatments);
                }
            }
        });
    }

    public void clearOnAction() {
        //preReportTableView.setItems(clearReport);
        preReport.removeAll();
        preReportTableView.getItems().clear();
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


}
