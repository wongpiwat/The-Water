package controllers;

import databases.TreatmentsDBConnector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
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
import javafx.stage.Stage;
import models.Account;
import models.Treatment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsController {
    private String year,month;
    @FXML private TableView preReportTableView,postReportTableView;
    @FXML private Tab preTreatmentTab,postTreatmentTab;
    @FXML private ChoiceBox yearChoiceBox,monthChoiceBox;
    @FXML private Label round, water, electricalEnergy, sediment, deodorizer, standard, electricalEnergyLabel, sedimentLabel, deodorizerLabel, standardLabel;

    public void initialize() {
        electricalEnergy.setText("");
        sediment.setText("");
        deodorizer.setText("");
        electricalEnergyLabel.setText("");
        sedimentLabel.setText("");
        deodorizerLabel.setText("");
        standardLabel.setText("");
        setReport(TreatmentsDBConnector.getPreTreatments(),preReportTableView);
        preTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                setReport(TreatmentsDBConnector.getPreTreatments(),preReportTableView);
                round.setText("");
                water.setText("");
                electricalEnergy.setText("");
                sediment.setText("");
                deodorizer.setText("");
                electricalEnergyLabel.setText("");
                sedimentLabel.setText("");
                deodorizerLabel.setText("");
                standardLabel.setText("");
                standard.setText("");
            }
        });
        postTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                setReport(TreatmentsDBConnector.getPostTreatments(),postReportTableView);
                round.setText("");
                water.setText("");
                electricalEnergy.setText("");
                sediment.setText("");
                deodorizer.setText("");
                electricalEnergyLabel.setText("Total Electrical Energy (kWH):");
                sedimentLabel.setText("Total Sediment (kg):");
                deodorizerLabel.setText("Total Deodorizer (kg):");
                standardLabel.setText("Total Standard (✓):");
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

    private void setReport(List<Treatment> treatments,TableView reportTableView) {
        ObservableList<Report> report = FXCollections.observableArrayList();
        ObservableList<String> months = FXCollections.observableArrayList();
        ObservableList<String> years = FXCollections.observableArrayList();
        List<Treatment> allTreatments, oneWeekTreatments , twoWeekTreatments,threeWeekTreatments,fourWeekTreatments,fiveWeekTreatments;
        allTreatments = new ArrayList<>();
        oneWeekTreatments = new ArrayList<>();
        twoWeekTreatments = new ArrayList<>();
        threeWeekTreatments = new ArrayList<>();
        fourWeekTreatments = new ArrayList<>();
        fiveWeekTreatments = new ArrayList<>();
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
                months.clear();
                reportTableView.getItems().clear();
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
                allTreatments.clear();
                report.clear();
                reportTableView.getItems().clear();
                if (year != null && monthNewValue != null) {
                    month = monthNewValue.toString();
                    for (int index = 0 ; index < treatments.size() ; index++) {
                        Date date = DateUtilities.getDate(treatments.get(index).getDateWater());
                        if (year.equals(DateUtilities.getYear(date)) && month.equals(DateUtilities.getMonth(date))) {
                            if (DateUtilities.getWeek(DateUtilities.getDate(treatments.get(index).getDateWater())) == 1)  {
                                oneWeekTreatments.add(treatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDate(treatments.get(index).getDateWater())) == 2)  {
                                twoWeekTreatments.add(treatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDate(treatments.get(index).getDateWater())) == 3)  {
                                threeWeekTreatments.add(treatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDate(treatments.get(index).getDateWater())) == 4)  {
                                fourWeekTreatments.add(treatments.get(index));
                            } else if (DateUtilities.getWeek(DateUtilities.getDate(treatments.get(index).getDateWater())) == 5)  {
                                fiveWeekTreatments.add(treatments.get(index));
                            }
                            allTreatments.add(treatments.get(index));
                        }
                    }
                    report.add(new Report("1",oneWeekTreatments.size()+"",CalculateUtilities.getAverageVolumeWater(oneWeekTreatments),CalculateUtilities.getAverageTemperature(oneWeekTreatments),CalculateUtilities.getAveragePH(oneWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(oneWeekTreatments),CalculateUtilities.getAverageVolumeSediment(oneWeekTreatments),CalculateUtilities.getAverageMLSS(oneWeekTreatments),CalculateUtilities.getAverageElectricalEnergy(oneWeekTreatments),CalculateUtilities.getAverageDeodorizerSystem(oneWeekTreatments),CalculateUtilities.getCountStandard(oneWeekTreatments)));
                    report.add(new Report("2",twoWeekTreatments.size()+"",CalculateUtilities.getAverageVolumeWater(twoWeekTreatments),CalculateUtilities.getAverageTemperature(twoWeekTreatments),CalculateUtilities.getAveragePH(twoWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(twoWeekTreatments),CalculateUtilities.getAverageVolumeSediment(twoWeekTreatments),CalculateUtilities.getAverageMLSS(twoWeekTreatments),CalculateUtilities.getAverageElectricalEnergy(twoWeekTreatments),CalculateUtilities.getAverageDeodorizerSystem(twoWeekTreatments),CalculateUtilities.getCountStandard(twoWeekTreatments)));
                    report.add(new Report("3",threeWeekTreatments.size()+"",CalculateUtilities.getAverageVolumeWater(threeWeekTreatments),CalculateUtilities.getAverageTemperature(threeWeekTreatments),CalculateUtilities.getAveragePH(threeWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(threeWeekTreatments),CalculateUtilities.getAverageVolumeSediment(threeWeekTreatments),CalculateUtilities.getAverageMLSS(threeWeekTreatments),CalculateUtilities.getAverageElectricalEnergy(threeWeekTreatments),CalculateUtilities.getAverageDeodorizerSystem(threeWeekTreatments),CalculateUtilities.getCountStandard(threeWeekTreatments)));
                    report.add(new Report("4",fourWeekTreatments.size()+"",CalculateUtilities.getAverageVolumeWater(fourWeekTreatments),CalculateUtilities.getAverageTemperature(fourWeekTreatments),CalculateUtilities.getAveragePH(fourWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(fourWeekTreatments),CalculateUtilities.getAverageVolumeSediment(fourWeekTreatments),CalculateUtilities.getAverageMLSS(fourWeekTreatments),CalculateUtilities.getAverageElectricalEnergy(fourWeekTreatments),CalculateUtilities.getAverageDeodorizerSystem(fourWeekTreatments),CalculateUtilities.getCountStandard(fourWeekTreatments)));
                    report.add(new Report("5",fiveWeekTreatments.size()+"",CalculateUtilities.getAverageVolumeWater(fiveWeekTreatments),CalculateUtilities.getAverageTemperature(fiveWeekTreatments),CalculateUtilities.getAveragePH(fiveWeekTreatments),CalculateUtilities.getAverageDissolvedOxygen(fiveWeekTreatments),CalculateUtilities.getAverageVolumeSediment(fiveWeekTreatments),CalculateUtilities.getAverageMLSS(fiveWeekTreatments),CalculateUtilities.getAverageElectricalEnergy(fiveWeekTreatments),CalculateUtilities.getAverageDeodorizerSystem(fiveWeekTreatments),CalculateUtilities.getCountStandard(fiveWeekTreatments)));
                    report.add(new Report("Average","",CalculateUtilities.getAverageVolumeWater(allTreatments),CalculateUtilities.getAverageTemperature(allTreatments),CalculateUtilities.getAveragePH(allTreatments),CalculateUtilities.getAverageDissolvedOxygen(allTreatments),CalculateUtilities.getAverageVolumeSediment(allTreatments),CalculateUtilities.getAverageMLSS(allTreatments),CalculateUtilities.getAverageElectricalEnergy(allTreatments),CalculateUtilities.getAverageDeodorizerSystem(allTreatments),""));
                    reportTableView.setItems(report);
                    round.setText(allTreatments.size()+"");
                    water.setText(CalculateUtilities.getTotalVolumeWater(allTreatments));
                    if (postTreatmentTab.isSelected()) {
                        electricalEnergy.setText(CalculateUtilities.getTotalElectricalEnergy(allTreatments));
                        sediment.setText(CalculateUtilities.getTotalVolumeSediment(allTreatments));
                        deodorizer.setText(CalculateUtilities.getTotalDeodorizerSystem(allTreatments));
                        standard.setText(CalculateUtilities.getCountStandard(allTreatments));
                    }
                    oneWeekTreatments.clear();
                    twoWeekTreatments.clear();
                    threeWeekTreatments.clear();
                    fourWeekTreatments.clear();
                    fiveWeekTreatments.clear();
                }
            }
        });
    }
}
