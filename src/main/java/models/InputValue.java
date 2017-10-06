package models;

public class InputValue {
    private double number = 0;


    public void appendNumber(String n) {
        if (number == 0) number = Double.parseDouble(n);

        else number = number * 10 + Double.parseDouble(n);
    }

    private String checkdot = "";
    private String check1 = "";
    private String check2 = "";

    public String addDot(String n) {
        if (n.equals(".")) {
            checkdot = ".";
        } else if ((!(n.equals("."))) && (checkdot.equals(".")) && (check1.equals(""))) {
            number = number + (Double.parseDouble(n) / 10);
            check1 = "1";
        } else if ((!(n.equals("."))) && (checkdot.equals(".")) && (check1.equals("1")) && (check2.equals(""))) {
            number = number + (Double.parseDouble(n) / 100);
            check2 = "2";
        }
        return checkdot;
    }

    public void deleteNumber() {
        if (check2.equals("2")) {
            number = ((number*100) - ((number*100)%10))/100;
            check2 = "";
        }
        else if ((check1.equals("1"))&&(check2.equals(""))){
            number = ((number*10) - ((number*10)%10))/10;
            check1 = "";
        }
        else{
            Double numfa = number % 10;
            number = number - numfa;
            number = number / 10;
            checkdot = "";
        }
    }


    public double getNumber() {
        return this.number = 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", number);
    }

    public void setCheckdot(String checkdot) {
        this.checkdot = checkdot;
    }

    public void setCheck1(String check1) {
        this.check1 = check1;
    }

    public void setCheck2(String check2) {
        this.check2 = check2;
    }
}