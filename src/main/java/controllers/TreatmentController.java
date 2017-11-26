package controllers;

import databases.PostTreatmentDatabase;
import databases.PreTreatmentDatabase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import models.Account;
import models.Treatment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TreatmentController implements Initializable {
    private Account account;
    static PreTreatmentDatabase PreTreatmentDatabase = new PreTreatmentDatabase();
    static PostTreatmentDatabase postTreatmentDatabase = new PostTreatmentDatabase();
    @FXML private TableView<Treatment> preTreatmentTableView,postTreatmentTableView, tableView;
    @FXML private Button deleteButton;
    @FXML private Tab preTreatmentTab,postTreatmentTab,tab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView = preTreatmentTableView;
        tab = preTreatmentTab;
        deleteButton.setDisable(true);
        preTreatmentTableView.setItems(PreTreatmentDatabase.loadItems());
        preTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                deleteButton.setDisable(false);
            }
        });
        postTreatmentTableView.setItems(postTreatmentDatabase.loadItems());
        postTreatmentTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Treatment>() {
            @Override
            public void changed(ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment newValue) {
                deleteButton.setDisable(false);
            }
        });
        preTreatmentTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (preTreatmentTab.isSelected()) {
                    postTreatmentTableView.getSelectionModel().select(null);
                    tab = preTreatmentTab;
                    tableView = preTreatmentTableView;
                    deleteButton.setDisable(true);
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
                    deleteButton.setDisable(true);
                }
            }
        });
    }

    public void createItem(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = null;
        loader = new FXMLLoader(getClass().getResource("/choose-treatment.fxml"));
        stage.setScene(new Scene(loader.load()));
        ChooseTreatmentController chooseTreatmentController = loader.getController();
        chooseTreatmentController.setUser(account);
        stage.show();
    }

    public void deleteItem() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete No." + tableView.getSelectionModel().getSelectedItem().getId() + " ?", ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK && tab.getText().equals("Pre Treatment")) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Information Dialog");
                informationAlert.setHeaderText("Look, an Information Dialog");
                informationAlert.setContentText("I have a great message for you!");
                informationAlert.showAndWait();
                PreTreatmentDatabase.deleteItem(tableView.getSelectionModel().getSelectedItem().getId());
                preTreatmentTableView.setItems(PreTreatmentDatabase.loadItems());
                deleteButton.setDisable(true);
            } else if (optional.get() == ButtonType.OK && tab.getText().equals("Post Treatment")) {
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Information Dialog");
                informationAlert.setHeaderText("Look, an Information Dialog");
                informationAlert.setContentText("I have a great message for you!");
                informationAlert.showAndWait();
                PostTreatmentDatabase.deleteItem(tableView.getSelectionModel().getSelectedItem().getId());
                postTreatmentTableView.setItems(postTreatmentDatabase.loadItems());
                deleteButton.setDisable(true);
            }
        }
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
