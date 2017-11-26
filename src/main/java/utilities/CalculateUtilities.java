package utilities;

import models.Treatment;

import java.util.List;

public class CalculateUtilities {

    public static double getAverageVolumeWater(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getVolumeWater();
        }
        return sum/list.size();
    }


    public static double getAverageTemperature(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getTemperature();
        }
        return sum/list.size();
    }

    public static double getAveragePH(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getpH();
        }
        return sum/list.size();
    }

    public static double getAverageDissolvedOxygen(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getDissolvedOxygen();
        }
        return sum/list.size();
    }

    public static double getAverageVolumeSediment(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getVolumeSediment();
        }
        return sum/list.size();
    }

    public static double getAverageMLSS(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getMlss();
        }
        return sum/list.size();
    }

    public static double getAverageElectricity(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getElectricity();
        }
        return sum/list.size();
    }

    public static double getAverageDeodorizerSystem(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            Treatment treatment = (Treatment) list.get(i);
            sum += ((Treatment) list.get(i)).getDeodorizerSystem();
        }
        return sum/list.size();
    }
}
