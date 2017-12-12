package models;

public class Standard {
    private double temperature;
    private int pH;
    private double dissolvedOxygen;
    private double mlss;

    public Standard(double temperature, int pH, double dissolvedOxygen, double mlss) {
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getpH() {
        return pH;
    }

    public double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public double getMlss() {
        return mlss;
    }
}
