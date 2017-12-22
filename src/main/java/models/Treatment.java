package models;

public class Treatment {
    private int id;
    private String dateForm;
    private String volumeWater;
    private String temperature;
    private String pH;
    private String dissolvedOxygen;
    private String volumeSediment;
    private String mlss;
    private String electricity;
    private String deodorizerSystem;
    private String standard;
    private String dateWater;
    private String accountUsername;

    public Treatment(int id, String dateWater, String volumeWater, String temperature, String pH, String dissolvedOxygen, String mlss, String dateForm, String accountUsername) {
        this.id = id;
        this.dateWater = dateWater;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
        this.dateForm = dateForm;
        this.accountUsername = accountUsername;
    }

    public Treatment(int id, String dateWater, String volumeWater, String temperature, String pH, String dissolvedOxygen, String volumeSediment, String mlss, String electricity, String deodorizerSystem, String standard, String dateForm, String accountUsername) {
        this.id = id;
        this.dateWater = dateWater;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.volumeSediment = volumeSediment;
        this.mlss = mlss;
        this.electricity = electricity;
        this.deodorizerSystem = deodorizerSystem;
        this.standard = standard;
        this.dateForm = dateForm;
        this.accountUsername = accountUsername;
    }

    public int getId() {
        return id;
    }

    public String getDateForm() {
        return dateForm;
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

    public String getDateWater() {
        return dateWater;
    }

    public String getAccountUsername() {
        return accountUsername;
    }
}
