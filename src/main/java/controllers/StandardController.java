package controllers;

import databases.StandardDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Account;
import models.Standard;

import java.io.IOException;
import java.util.Optional;

public class StandardController {
    private Account account;
    private Standard standard;
    @FXML private Label pH,bod,sulfide,settleableSolids,totalDissolvedSolid,suspendedSoilds,fatOilGrease,totalKjeldahlNitrogen;
    @FXML private Button deleteButton,createButton;

    public void initialize() {
        standard = StandardDBConnector.loadStandardToTable();
        if (standard != null) {
            deleteButton.setDisable(false);
            createButton.setDisable(true);
            pH.setText(standard.getpH()+"");
            bod.setText(standard.getBod()+"");
            sulfide.setText(standard.getSulfide()+"");
            settleableSolids.setText(standard.getSettleableSolids()+"");
            totalDissolvedSolid.setText(standard.getTotalDissolvedSolid()+"");
            suspendedSoilds.setText(standard.getSuspendedSoilds()+"");
            fatOilGrease.setText(standard.getFatOilGrease()+"");
            totalKjeldahlNitrogen.setText(standard.getTotalKjeldahlNitrogen()+"");
        } else {
            deleteButton.setDisable(true);
            createButton.setDisable(false);
        }
    }

    public void createStandard(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateStandardView.fxml"));
        stage.setScene(new Scene(loader.load()));
        CreateStandardController createStandardController = loader.getController();
        createStandardController.setStandard(standard);
        createStandardController.setUser(account);
        stage.show();
    }

    public void deleteStandard() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete ?", ButtonType.OK, ButtonType.CANCEL);
        Optional optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
            informationAlert.setTitle("Information Dialog");
            informationAlert.setHeaderText("Look, an Information Dialog");
            informationAlert.setContentText("I have a great message for you!");
            informationAlert.showAndWait();
            StandardDBConnector.deleteStandard();
            pH.setText("");
            bod.setText("");
            sulfide.setText("");
            settleableSolids.setText("");
            totalDissolvedSolid.setText("");
            suspendedSoilds.setText("");
            fatOilGrease.setText("");
            totalKjeldahlNitrogen.setText("");
            deleteButton.setDisable(true);
            createButton.setDisable(false);
        }
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

