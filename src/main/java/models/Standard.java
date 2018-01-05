package models;

public class Standard {
    private int no;
    private String release;
    private double temperature;
    private double pH;
    private double dissolvedOxygen;
    private double mlss;
    private String name;
    private String date;

    public Standard(int no, String release, double temperature, double pH, double dissolvedOxygen, double mlss, String name, String date) {
        this.no = no;
        this.release = release;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
        this.name = name;
        this.date = date;
    }

    public String checkStandard(double temperature, double pH, double dissolvedOxygen, double mlss) {
        if (temperature <= this.temperature && pH <= this.pH && dissolvedOxygen <= this.dissolvedOxygen && mlss <= this.mlss) {
            return "✓";
        }
        return "✕";
    }

    public int getNo() {
        return no;
    }

    public String getRelease() {
        return release;
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

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
