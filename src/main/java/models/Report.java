package models;

public class Report {
    private String week;
    private int round;
    private String volumeWater;
    private String temperature;
    private String pH;
    private String dissolvedOxygen;
    private String volumeSediment;
    private String mlss;
    private String electricity;
    private String deodorizerSystem;
    private String standard;

    public Report(String week, int round, String volumeWater, String temperature, String pH, String dissolvedOxygen, String volumeSediment, String mlss, String electricity, String deodorizerSystem, String standard) {
        this.week = week;
        this.round = round;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.volumeSediment = volumeSediment;
        this.mlss = mlss;
        this.electricity = electricity;
        this.deodorizerSystem = deodorizerSystem;
        this.standard = standard;
    }

    public String getWeek() {
        return week;
    }

    public int getRound() {
        return round;
    }

    public String getVolumeWater() {
        return volumeWater;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPH() {
        return pH;
    }

    public String getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public String getVolumeSediment() {
        return volumeSediment;
    }

    public String getMlss() {
        return mlss;
    }

    public String getElectricity() {
        return electricity;
    }

    public String getDeodorizerSystem() {
        return deodorizerSystem;
    }

    public String getStandard() {
        return standard;
    }
}
