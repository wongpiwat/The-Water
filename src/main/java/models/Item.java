package models;

public class Item {
    private int no;
    private String date;
    private int numberMachine;
    private String round;
    private double dissolvedOxygen;
    private double celsius;
    private double volumeWater;
    private double volumeSludge;
    private boolean wasteWaterMachine;
    private boolean waterPump;
    private boolean aerator;
    private boolean sludgeDewateringMachine;

    public Item(int no, String date, int numberMachine, String round, double dissolvedOxygen, double celsius, double volumeWater, double volumeSludge, boolean wasteWaterMachine, boolean waterPump, boolean aerator, boolean sludgeDewateringMachine) {
        this.no = no;
        this.date = date;
        this.numberMachine = numberMachine;
        this.round = round;
        this.dissolvedOxygen = dissolvedOxygen;
        this.celsius = celsius;
        this.volumeWater = volumeWater;
        this.volumeSludge = volumeSludge;
        this.wasteWaterMachine = wasteWaterMachine;
        this.waterPump = waterPump;
        this.aerator = aerator;
        this.sludgeDewateringMachine = sludgeDewateringMachine;
    }

    public int getNo() {
        return no;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberMachine() {
        return numberMachine;
    }

    public void setNumberMachine(int numberMachine) {
        this.numberMachine = numberMachine;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(double dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double getVolumeWater() {
        return volumeWater;
    }

    public void setVolumeWater(double volumeWater) {
        this.volumeWater = volumeWater;
    }

    public double getVolumeSludge() {
        return volumeSludge;
    }

    public void setVolumeSludge(double volumeSludge) {
        this.volumeSludge = volumeSludge;
    }

    public boolean isWasteWaterMachine() {
        return wasteWaterMachine;
    }

    public void setWasteWaterMachine(boolean wasteWaterMachine) {
        this.wasteWaterMachine = wasteWaterMachine;
    }

    public boolean isWaterPump() {
        return waterPump;
    }

    public void setWaterPump(boolean waterPump) {
        this.waterPump = waterPump;
    }

    public boolean isAerator() {
        return aerator;
    }

    public void setAerator(boolean aerator) {
        this.aerator = aerator;
    }

    public boolean isSludgeDewateringMachine() {
        return sludgeDewateringMachine;
    }

    public void setSludgeDewateringMachine(boolean sludgeDewateringMachine) {
        this.sludgeDewateringMachine = sludgeDewateringMachine;
    }
}
