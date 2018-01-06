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
                if ((textField.getText().charAt(i) + "").matches("[a-zA-Z ]")) {
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
        try {
            if (Double.parseDouble(textField.getText()) > 3000000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            System.err.println("Error isCorrectWater");
        }
        return false;
    }

    public static boolean isCorrectTemp(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 100 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            System.err.println("Error isCorrectTemp");
        }
        return false;
    }

    public static boolean isCorrectPH(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 14 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            System.err.println("Error isCorrectPH");
        }
        return false;
    }

    public static boolean isCorrectDate(DatePicker datePicker) {
        int dayDatePicker = datePicker.getValue().getDayOfMonth();
        int day = LocalDate.now().getDayOfMonth();
        int monthDatePicker = datePicker.getValue().getMonthValue();
        int month = LocalDate.now().getMonthValue();
        int yearDatePicker = datePicker.getValue().getYear();
        int year = LocalDate.now().getYear();
        if ((monthDatePicker == month) && (yearDatePicker == year)) {
            if (dayDatePicker <= day) {
                if (dayDatePicker >= day-7) {
                    datePicker.setStyle("");
                    return true;
                }
            }
        }
        datePicker.setStyle("-fx-border-color: red");
        return false;
    }

    public static boolean isCorrectTime(DatePicker datePicker, ComboBox hourComboBox, ComboBox minuteComboBox) {
        int dayDatePicker = datePicker.getValue().getDayOfMonth();
        int day = LocalDate.now().getDayOfMonth();
        int hComboBox = Integer.parseInt(hourComboBox.getValue().toString());
        int hour = LocalTime.now().getHour();
        int mComboBox = Integer.parseInt(minuteComboBox.getValue().toString());
        int minute = LocalTime.now().getMinute();
        if (dayDatePicker == day) {
            if (hComboBox <= hour && mComboBox <= minute) {
                hourComboBox.setStyle("");
                minuteComboBox.setStyle("");
                return true;
            }
            hourComboBox.setStyle("-fx-border-color: red");
            minuteComboBox.setStyle("-fx-border-color: red");
            return false;
        } else if (dayDatePicker < day) {
            hourComboBox.setStyle("");
            minuteComboBox.setStyle("");
            return true;
        }
        hourComboBox.setStyle("-fx-border-color: red");
        minuteComboBox.setStyle("-fx-border-color: red");
        return false;
    }
}
