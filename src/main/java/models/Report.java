package models;

import javafx.beans.property.SimpleDoubleProperty;

public class Report {
    private int week;
    private double volumeWater;
    private double temperature;
    private SimpleDoubleProperty pH = new SimpleDoubleProperty();    private double dissolvedOxygen;
    private double volumeSediment;
    private double mlss;
    private double electricity;
    private double deodorizerSystem;

    public Report(int week, double volumeWater, double temperature, double pH, double dissolvedOxygen, double mlss) {
        this.week = week;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH.set(pH);
        this.dissolvedOxygen = dissolvedOxygen;
        this.mlss = mlss;
    }

    public Report(int week, double volumeWater, double temperature, double pH, double dissolvedOxygen, double volumeSediment, double mlss, double electricity, double deodorizerSystem) {
        this.week = week;
        this.volumeWater = volumeWater;
        this.temperature = temperature;
        this.pH.set(pH);
        this.dissolvedOxygen = dissolvedOxygen;
        this.volumeSediment = volumeSediment;
        this.mlss = mlss;
        this.electricity = electricity;
        this.deodorizerSystem = deodorizerSystem;
    }

    public int getWeek() {
        return week;
    }

    public double getVolumeWater() {
        return volumeWater;
    }

    public double getTemperature() {
        return temperature;
    }


    public double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public double getVolumeSediment() {
        return volumeSediment;
    }

    public double getMlss() {
        return mlss;
    }

    public double getElectricity() {
        return electricity;
    }

    public double getDeodorizerSystem() {
        return deodorizerSystem;
    }

    public double getpH() {
        return pH.get();
    }

    public SimpleDoubleProperty pHProperty() {
        return pH;
    }
}
