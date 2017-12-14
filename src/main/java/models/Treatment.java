package models;

public class Treatment {
    private int id;
    private String date;
    private String volumeWater;
    private String temperature;
    private String pH;
    private String dissolvedOxygen;
    private String volumeSediment;
    private String mlss;
    private String electricity;
    private String deodorizerSystem;

    public Treatment(int id, String date, String volumeWater, String temperature, String pH, String dissolvedOxygen, String mlss) {
        this.id = id;
        this.date = date;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
    }

    public Treatment(int id, String date, String volumeWater, String temperature, String pH, String dissolvedOxygen, String volumeSediment, String mlss, String electricity, String deodorizerSystem) {
        this.id = id;
        this.date = date;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.volumeSediment = volumeSediment;
        this.mlss = mlss;
        this.electricity = electricity;
        this.deodorizerSystem = deodorizerSystem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getVolumeWater() {
        return volumeWater;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getpH() {
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
