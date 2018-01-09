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
        if (textField.getText().equals("") || textField.getText().equals(" ") || textField.getText().equals(".")) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        for (int i = 0; i < textField.getText().length(); i++) {
            if (!(textField.getText().charAt(i) + "").matches("[0-9.]")) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isAllCharacter(TextField textField) {
        if (textField.getText().equals("") || textField.getText().equals(" ") || textField.getText().equals(".")) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        for (int i = 0; i < textField.getText().length(); i++) {
            if (!(textField.getText().charAt(i) + "").matches("[a-zA-Z ]")) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isAllCharacterNumber(TextField textField) {
        if (textField.getText().equals("") || textField.getText().equals(" ") || textField.getText().equals(".")) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
        for (int i = 0; i < textField.getText().length(); i++) {
            if (!(textField.getText().charAt(i) + "").matches("[0-9a-zA-Z]")) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
        }
        textField.setStyle("");
        return true;
    }

    public static boolean isAllCorrectType(List<Boolean> list) {
        for (boolean i : list){
            if (!i){
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
            textField.setStyle("-fx-border-color: red");
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
            textField.setStyle("-fx-border-color: red");
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
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectPH");
        }
        return false;
    }

    public static boolean isCorrectDO(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 1000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectDO");
        }
        return false;
    }


    public static boolean isCorrectVolumeSediment(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 1000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectVolumeSediment");
        }
        return false;
    }

    public static boolean isCorrectMLSS(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 1000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectMLSS");
        }
        return false;
    }

    public static boolean isCorrectElectricalEnergy(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 1000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectElectrical Energy");
        }
        return false;
    }

    public static boolean isCorrectDeodorizerSystem(TextField textField) {
        try {
            if (Double.parseDouble(textField.getText()) > 1000 || Double.parseDouble(textField.getText()) < 0) {
                textField.setStyle("-fx-border-color: red");
                return false;
            }
            textField.setStyle("");
            return true;
        } catch (Exception e) {
            textField.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectDeodorizerSystem");
        }
        return false;
    }

    public static boolean isCorrectDate(DatePicker datePicker) {
        try {
            int dayDatePicker = datePicker.getValue().getDayOfMonth();
            int day = LocalDate.now().getDayOfMonth();
            int monthDatePicker = datePicker.getValue().getMonthValue();
            int month = LocalDate.now().getMonthValue();
            int yearDatePicker = datePicker.getValue().getYear();
            int year = LocalDate.now().getYear();
            if ((monthDatePicker == month) && (yearDatePicker == year)) {
                if (dayDatePicker <= day) {
                    if (dayDatePicker >= day - 7) {
                        datePicker.setStyle("");
                        return true;
                    } else {
                        datePicker.setStyle("-fx-border-color: red");
                        System.err.println("Error isCorrectDate");
                    }
                } else {
                    datePicker.setStyle("-fx-border-color: red");
                    System.err.println("Error isCorrectDate");
                }
            }
        } catch (Exception e) {
            datePicker.setStyle("-fx-border-color: red");
            System.err.println("Error isCorrectDate");
        }
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
            if (hComboBox == hour && mComboBox <= minute) {
                hourComboBox.setStyle("");
                minuteComboBox.setStyle("");
                return true;
            } else if (hComboBox < hour) {
                hourComboBox.setStyle("");
                minuteComboBox.setStyle("");
                return true;
            }
            hourComboBox.setStyle("-fx-border-color: red");
            minuteComboBox.setStyle("-fx-border-color: red");
            return false;
        } else if (dayDatePicker >= day-7) {
            hourComboBox.setStyle("");
            minuteComboBox.setStyle("");
            return true;
        }
        datePicker.setStyle("-fx-border-color: red");
        hourComboBox.setStyle("-fx-border-color: red");
        minuteComboBox.setStyle("-fx-border-color: red");
        return false;
    }
}
