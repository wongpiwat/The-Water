package utilities;

import models.Treatment;

import java.util.List;

public class CalculateUtilities {

    public static double getAverageVolumeWater(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getVolumeWater();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }
    }


    public static double getAverageTemperature(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getTemperature();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }
    }

    public static double getAveragePH(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getpH();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }

    public static double getAverageDissolvedOxygen(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getDissolvedOxygen();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }

    public static double getAverageVolumeSediment(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getVolumeSediment();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }

    public static double getAverageMLSS(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getMlss();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }

    public static double getAverageElectricity(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getElectricity();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }

    public static double getAverageDeodorizerSystem(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += ((Treatment) list.get(i)).getDeodorizerSystem();
        }
        double value = sum/list.size();
        String string = value+"";
        if (string.equals("NaN")) {
            return 0;
        } else {
            return sum/list.size();
        }    }
}
