package utilities;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Account;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CheckInput {

    public static boolean isAllNumber(TextField textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.getText().length(); i++) {
            if (isCorrect) {
                if ((textField.getText().charAt(i) + "").matches("[0-9.]")) {
                } else {
                    isCorrect = false;
                    textField.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        textField.setStyle("");
        return isCorrect;
    }

    public static boolean isAllCharacter(TextField textField) {
        boolean isCorrect = true;
        for (int i = 0; i < textField.getText().length(); i++) {
            if (isCorrect) {
                if ((textField.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    textField.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        textField.setStyle("");
        return isCorrect;
    }

    public static boolean isAllCorrectType(List<Boolean> list) {
        for (boolean i : list){
            if (!i){
                return false;
            }
        }
        return true;
    }

    public static boolean isAllCorrectEmpty(List<String> list) {
        for (String i : list) {
            if (i.equals("") || i.equals(" ") || i.equals(".")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrectUsername(List<Account> list, TextField textField) {
        for (Account i:list) {
            if (i.getUsername().equals(textField.getText())) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isCorrectWater(TextField textField) {
        if (Double.parseDouble(textField.getText()) > 3000000 || Double.parseDouble(textField.getText()) < 0) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isCorrectTemp(TextField textField) {
        if (Double.parseDouble(textField.getText()) > 100 || Double.parseDouble(textField.getText()) < 0) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isCorrectPH(TextField textField) {
        if (Double.parseDouble(textField.getText()) > 14 || Double.parseDouble(textField.getText()) < 0) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isCorrectDate(DatePicker datePicker) {
        if (datePicker.getValue().getDayOfMonth() > LocalDate.now().getDayOfMonth() || datePicker.getValue().getMonthValue() > LocalDate.now().getMonthValue() || datePicker.getValue().getYear() > LocalDate.now().getYear()) {
            datePicker.setStyle("-fx-border-color: red");
            return false;
        }
        datePicker.setStyle("");
        return true;
    }

    public static boolean isCorrectTime(ComboBox hour, ComboBox minute) {
        if (Integer.parseInt(hour.getValue().toString()) > LocalTime.now().getHour() || Integer.parseInt(minute.getValue().toString()) > LocalTime.now().getMinute()) {
            hour.setStyle("-fx-border-color: red");
            minute.setStyle("-fx-border-color: red");
            return false;
        }
        hour.setStyle("");
        minute.setStyle("");
        return true;
    }
}
