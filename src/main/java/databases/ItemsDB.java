package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Item;

import java.sql.*;

public class ItemsDB {
    public static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList loadItems() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Item";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int no = resultSet.getInt(1);
                    String date = resultSet.getString(2);
                    int numberMachine = resultSet.getInt(3);
                    String round = resultSet.getString(4);
                    double dissolvedOxygen = resultSet.getDouble(5);
                    double celsius = resultSet.getDouble(6);
                    double volumeWater = resultSet.getDouble(7);
                    double volumeSludge = resultSet.getDouble(8);
                    String wasteWaterMachine = resultSet.getString(9);
                    String waterPump = resultSet.getString(10);
                    String aerator = resultSet.getString(11);
                    String sludgeDewateringMachine = resultSet.getString(12);
                    items.add(new Item(no, date, numberMachine, round, dissolvedOxygen,celsius,volumeWater,volumeSludge,Boolean.parseBoolean(wasteWaterMachine),Boolean.parseBoolean(waterPump),Boolean.parseBoolean(aerator),Boolean.parseBoolean(sludgeDewateringMachine)));
                }
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public static void saveItem(String Date, int numberMachine, String round, double dissolvedOxygen,double celsius,double VolumeWater,double VolumeSludge,boolean WasteWaterMachine, boolean WaterPump, boolean Aerator, boolean sludgeDewateringMachine) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Item (Date, NumberMachine, Round, DissolvedOxygen, Celsius, VolumeWater, VolumeSludge, WasteWaterMachine, WaterPump, Aerator, SludgeDewateringMachine) values (\'" + Date + "\', \'" + numberMachine + " \', \'" + round + " \', \' " + dissolvedOxygen + " \' , \' " + celsius + "\', \'" + VolumeWater + "\' , \'" + VolumeSludge + "\' ,\'" + WasteWaterMachine + "\' ,\'" + WaterPump + "\' ,\'" + Aerator + "\' ,\'" + sludgeDewateringMachine + "\')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteItem(int no) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Item where No == \'" + no + "\'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editItem(int no,String date, String dissolvedOxygen,String celsius,String volume) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Item set Date=\'" + date + "\' ,DissolvedOxygen=\'" + dissolvedOxygen + "\' ,Celsius=\'" + celsius + "\' ,Volume=\'" + volume + "\' where No == \'" + no + "\'";
                System.out.println(query);
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCreateID() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select max(seq) from sqlite_sequence";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int minID = resultSet.getInt(1);
                connection.close();
                return minID + 1;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}

