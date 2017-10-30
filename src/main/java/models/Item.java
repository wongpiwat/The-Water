package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleIntegerProperty no = new SimpleIntegerProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty dissolvedOxygen = new SimpleStringProperty();
    private SimpleStringProperty celsius = new SimpleStringProperty();
    private SimpleStringProperty volume = new SimpleStringProperty();

    public Item(int no, String date, String dissolvedOxygen,String celsius,String volume) {
        setNo(no);
        setDate(date);
        setDissolvedOxygen(dissolvedOxygen);
        setCelsius(celsius);
        setVolume(volume);
    }

    public int getNo() {
        return no.get();
    }

    public void setNo(int id) { this.no.set(id); }

    public String getDate() { return date.get(); }

    public void setDate(String nameFood) {
        this.date.set(nameFood);
    }

    public String getDissolvedOxygen() {
        return dissolvedOxygen.get();
    }

    public void setDissolvedOxygen(String dissolvedOxygen) {
        this.dissolvedOxygen.set(dissolvedOxygen);
    }

    public String getCelsius() {
        return celsius.get();
    }

    public void setCelsius(String celsius) {
        this.celsius.set(celsius);
    }

    public String getVolume() {
        return volume.get();
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }
}
