package utilities;

import javafx.scene.control.TextField;

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

    public static boolean isAllCorrect(List<Boolean> list) {
        for (boolean i : list){
            if (!i){
                return false;
            }
        }
        return true;
    }
}
