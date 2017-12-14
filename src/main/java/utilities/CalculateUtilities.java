package utilities;

import models.Treatment;

import java.text.DecimalFormat;
import java.util.List;

public class CalculateUtilities {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static String getAverageVolumeWater(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getVolumeWater());
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageTemperature(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getTemperature());
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAveragePH(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getPH());
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageDissolvedOxygen(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getDissolvedOxygen());
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageVolumeSediment(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if ( ((Treatment) list.get(i)).getVolumeSediment() != null ) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getVolumeSediment());
            }
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageMLSS(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getMlss());
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageElectricity(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getElectricity() != null) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getElectricity());
            }
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getAverageDeodorizerSystem(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getDeodorizerSystem() != null) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getDeodorizerSystem());
            }
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }
}
