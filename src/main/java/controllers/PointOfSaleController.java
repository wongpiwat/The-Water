package controllers;

import javafx.scene.control.TextInputDialog;
import models.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.InputValue;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static controllers.SalesManagementController.edit;

public class PointOfSaleController implements Initializable{
    private InputValue inputValue = new InputValue();
    private double netBaht = 0;
    private double taxBaht = 0;
    private double totalBaht = 0;
    private boolean isCashier;

    @FXML
    private Label netLabel, cashLabel, totalLabel, texLabel, changeLabel;
    @FXML
    private Button payButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, deleteButton, enterButton, dotButton, logoutButton, backspaceButton;

    public PointOfSaleController() {
        isCashier = true;
    }
    @FXML
    private void handleBtnNumber0() {
        if (inputValue.addDot( "0" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "0" );
        }
    }
    @FXML
    private void handleBtnNumber1() {
        if (inputValue.addDot( "1" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "1" );
        }
    }
    @FXML
    private void handleBtnNumber2() {
        if (inputValue.addDot( "2" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "2" );
        }
    }
    @FXML
    private void handleBtnNumber3() {
        if (inputValue.addDot( "3" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "3" );
        }
    }
    @FXML
    private void handleBtnNumber4() {
        if (inputValue.addDot( "4" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "4" );
        }
    }
    @FXML
    private void handleBtnNumber5() {
        if (inputValue.addDot( "5" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "5" );
        }
    }
    @FXML
    private void handleBtnNumber6() {
        if (inputValue.addDot( "6" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "6" );
        }
    }
    @FXML
    private void handleBtnNumber7() {
        if (inputValue.addDot( "7" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "7" );
        }
    }
    @FXML
    public void handleBtnNumber8() {
        if (inputValue.addDot( "8" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "8" );
        }
    }
    @FXML
    private void handleBtnNumber9() {
        if (inputValue.addDot( "9" ).equals( "." )) {
            cashLabel.setText( inputValue.toString() );
        } else {
            appendNumberToInput( "9" );
        }
    }
    @FXML
    public void handleBtnNumberDot() {
        inputValue.addDot( "." );
        cashLabel.setText( inputValue.toString() );
    }
    @FXML
    private void handleBtnCE() {
        inputValue.setCheckdot("");
        inputValue.setCheck1("");
        inputValue.setCheck2("");
        inputValue.getNumber();
        cashLabel.setText(inputValue.toString());
    }
    @FXML
    private void handleBtnDelete() {
        inputValue.deleteNumber();
        cashLabel.setText(inputValue.toString());
    }
    @FXML
    private void handleBtnLogout(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        if (isCashier){
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "../login.fxml" ) );
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "../home.fxml" ) );
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
    }
    @FXML
    private void handleBtnEnter() {
        if (cashLabel.getText().equals("")){
            cashLabel.setText("0.00");
        }
        if (Double.parseDouble(cashLabel.getText()) >= totalBaht){
            double enterBaht = Double.parseDouble(cashLabel.getText());
            double change = enterBaht-totalBaht;
            changeLabel.setText(String.format("%.2f",Float.parseFloat(String.valueOf(change))));
            this.payButton.setDisable(false);
        } else {
            this.payButton.setDisable(true);
        }
    }

    private void appendNumberToInput(String n) {
        inputValue.appendNumber(n);
        cashLabel.setText(inputValue.toString());
    }

    @FXML
    private void handleBtnVoucher(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Voucher");
        dialog.setHeaderText(null);
        dialog.setContentText("Voucher Code:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(voucherCode -> System.out.println("Voucher Code: " + voucherCode));
    }

    @FXML
    private TableView<Menu> posTableView,customerOrderTableView;

    private ObservableList<Menu> listOrder = FXCollections.observableArrayList();

    public void bthPay(){
        listOrder = FXCollections.observableArrayList();
        customerOrderTableView.setItems(listOrder);
        netBaht = 0;
        taxBaht = 0;
        totalBaht = 0;
        netLabel.setText(String.format("%.2f",netBaht));
        texLabel.setText(String.format("%.2f",taxBaht));
        totalLabel.setText(String.format("%.2f",totalBaht));
        cashLabel.setText("");
        changeLabel.setText("0.00");
        this.handleBtnCE();
        this.payButton.setDisable(true);
        this.enterButton.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.enterButton.setDisable(true);
        this.payButton.setDisable(true);
        posTableView.setItems(edit.loadMenu());
        posTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    this.addMenuToOrder();
                }else{
                    this.addMenuToOrder();
                }
            }
            private void addMenuToOrder(){
                if (posTableView.getSelectionModel().getSelectedItem() != null){
                    listOrder.add(posTableView.getSelectionModel().getSelectedItem());
                    customerOrderTableView.setItems(listOrder);
                    double currentNetBaht = posTableView.getSelectionModel().getSelectedItem().getPrice();
                    double currentTaxBaht = (currentNetBaht*7)/100;
                    double currentTotalBaht = currentNetBaht+currentTaxBaht;
                    netBaht += currentNetBaht;
                    taxBaht += currentTaxBaht;
                    totalBaht += currentTotalBaht;
                    netLabel.setText(String.format("%.2f",netBaht));
                    texLabel.setText(String.format("%.2f",taxBaht));
                    totalLabel.setText(String.format("%.2f",totalBaht));
                    enterButton.setDisable(false);
                }
            }
        });
        customerOrderTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.deleteMenuOrder();
                } else {
                    this.deleteMenuOrder();
                }
            }
            private void deleteMenuOrder(){
                if (customerOrderTableView.getSelectionModel().getSelectedItem() != null){
                    double currentNetBaht = customerOrderTableView.getSelectionModel().getSelectedItem().getPrice();
                    double currentTaxBaht = (currentNetBaht*7)/100;
                    double currentTotalBaht = currentNetBaht+currentTaxBaht;
                    listOrder.remove(customerOrderTableView.getSelectionModel().getSelectedIndex());
                    customerOrderTableView.setItems(listOrder);
                    netBaht -= currentNetBaht;
                    taxBaht -= currentTaxBaht;
                    totalBaht -= currentTotalBaht;
                    netLabel.setText(String.format("%.2f",netBaht));
                    texLabel.setText(String.format("%.2f",taxBaht));
                    totalLabel.setText(String.format("%.2f",totalBaht));
                    if(!listOrder.isEmpty()){
                        enterButton.setDisable(false);
                    } else {
                        enterButton.setDisable(true);
                    }
                }
            }
        });
    }

    public void setCashier(boolean cashier) {
        isCashier = cashier;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
