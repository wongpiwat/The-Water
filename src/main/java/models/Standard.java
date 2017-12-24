package models;

public class Standard {
    private double temperature;
    private double pH;
    private double dissolvedOxygen;
    private double mlss;

    public Standard(double temperature, double pH, double dissolvedOxygen, double mlss) {
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
    }

    public String checkStandard(double temperature, double pH, double dissolvedOxygen, double mlss) {
        if (temperature <= this.temperature && pH <= this.pH && dissolvedOxygen <= this.dissolvedOxygen && mlss <= this.mlss) {
            return "P";
        }
        return "F";
    }

    public double getTemperature() {
        return temperature;
    }

    public double getpH() {
        return pH;
    }

    public double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public double getMlss() {
        return mlss;
    }
}
