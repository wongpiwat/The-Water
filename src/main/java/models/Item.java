package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty nameFood = new SimpleStringProperty("");
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);

//    public Menu(String type, String nameFood, int quantity, double price) {
//        setType(type);
//        setNameFood(nameFood);
//        setPrice(price);
//    }

    public Item(int id, String nameFood, double price) {
        setId(id);
        setNameFood(nameFood);
        setPrice(price);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) { this.id.set(id); }

    public String getNameFood() { return nameFood.get(); }

    public void setNameFood(String nameFood) {
        this.nameFood.set(nameFood);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
//
//    public int getQuantity() { return quantity.get(); }
//
//    public void setQuantity(int quantity) { this.quantity.set(quantity); }
}
