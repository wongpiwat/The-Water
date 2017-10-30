package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Item;

import java.sql.*;

public class ItemsDB {
    public static String dbURL = "jdbc:sqlite:Item.db";

    public static ObservableList loadDB() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Item";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int no = resultSet.getInt(1);
                    String date = resultSet.getString(2);
                    String dissolvedOxygen = resultSet.getString(3);
                    String celsius = resultSet.getString(4);
                    String volume = resultSet.getString(5);
                    items.add(new Item(no, date, dissolvedOxygen,celsius,volume));
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

    public static void saveDB(String Date, String dissolvedOxygen,String celsius,String volume) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Item(Date, DissolvedOxygen, Celsius, Volume) values (\'" + Date + "\',\'" + dissolvedOxygen + "\',\'" + celsius + "\',\'" + volume + "\')";
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

    public static void deleteDB(int no) {
        try {
            Class.forName("org.sqlite.JDBC");
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

    public static void editDB(int no,String date, String dissolvedOxygen,String celsius,String volume) {
        try {
            Class.forName("org.sqlite.JDBC");
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
            Class.forName("org.sqlite.JDBC");
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

