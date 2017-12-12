package models;

public class Report {
    private String week;
    private String volumeWater;
    private String temperature;
    private String pH;
    private String dissolvedOxygen;
    private String volumeSediment;
    private String mlss;
    private String electricity;
    private String deodorizerSystem;

    public Report(String week, String volumeWater, String temperature, String pH, String dissolvedOxygen, String volumeSediment, String mlss, String electricity, String deodorizerSystem) {
        this.week = week;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.volumeSediment = volumeSediment;
        this.mlss = mlss;
        this.electricity = electricity;
        this.deodorizerSystem = deodorizerSystem;
    }

    public String getWeek() {
        return week;
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
}
