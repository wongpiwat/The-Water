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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
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
        String string = sum/list.size()+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return decimalFormat.format(sum/list.size());
        }
    }

    public static String getCountStandard(List list) {
        String word;
        int count = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getDeodorizerSystem() != null) {
                word = (((Treatment) list.get(i)).getStandard());
                if (word != null && word.equals("P") ) {
                    count ++;
                }
            }
        }
        return count+"";
    }

}
