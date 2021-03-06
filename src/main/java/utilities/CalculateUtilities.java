package utilities;

import models.Treatment;

import java.util.List;

public class CalculateUtilities {

    public static String getAverageVolumeWater(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getVolumeWater());
        }
        String string = sum/list.size()+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
        }
    }

    public static String getAverageElectricalEnergy(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getElectricalEnergy() != null) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getElectricalEnergy());
            }
        }
        String string = sum/list.size()+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum/list.size());
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
            return String.format("%.2f",sum/list.size());
        }
    }

    public static String getCountStandard(List list) {
        String word;
        int count = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getDeodorizerSystem() != null) {
                word = (((Treatment) list.get(i)).getStandard());
                if (word != null && word.equals("✓") ) {
                    count ++;
                }
            }
        }
        return count+"";
    }

    public static String getTotalVolumeWater(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            sum += Double.parseDouble(((Treatment) list.get(i)).getVolumeWater());
        }
        String string = sum+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum);
        }
    }

    public static String getTotalVolumeSediment(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if ( ((Treatment) list.get(i)).getVolumeSediment() != null ) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getVolumeSediment());
            }
        }
        String string = sum+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum);
        }
    }

    public static String getTotalElectricalEnergy(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getElectricalEnergy() != null) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getElectricalEnergy());
            }
        }
        String string = sum+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum);
        }
    }

    public static String getTotalDeodorizerSystem(List list) {
        double sum = 0;
        for (int i = 0; i<list.size();i++) {
            if (((Treatment) list.get(i)).getDeodorizerSystem() != null) {
                sum += Double.parseDouble(((Treatment) list.get(i)).getDeodorizerSystem());
            }
        }
        String string = sum+"";
        if (string.equals("NaN")) {
            return "0";
        } else {
            return String.format("%.2f",sum);
        }
    }

}
