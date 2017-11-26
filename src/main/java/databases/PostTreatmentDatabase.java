package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Treatment;

import java.sql.*;

public class PostTreatmentDatabase {
    public static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList loadItems() {
        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from PostTreatment";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String date = resultSet.getString("Date");
                    double volumeWater = resultSet.getDouble("VolumeWater");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double volumeSediment = resultSet.getDouble("VolumeSediment");
                    double mlss = resultSet.getDouble("MLSS");
                    double electricity = resultSet.getDouble("Electricity");
                    double deodorizerSystem = resultSet.getDouble("DeodorizerSystem");
                    treatments.add(new Treatment(id, date, volumeWater, temperature, pH,dissolvedOxygen,volumeSediment,mlss,electricity,deodorizerSystem));
                }
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }

    public static void saveItem(String Date, double volumeWater,double temperature,double pH,double dissolvedOxygen,double volumeSediment,double mlss,double electricity,double deodorizerSystem) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into PostTreatment (Date, VolumeWater, Temperature, pH, DissolvedOxygen, VolumeSediment, MLSS, Electricity, DeodorizerSystem) values (\'" + Date + "\', \'" + volumeWater + " \', \'" + temperature + " \', \' " + pH + " \', \' " + dissolvedOxygen + " \' , \' "+ volumeSediment + "\' , \' " + mlss + " \' , \' " + electricity + " \' , \' " + deodorizerSystem + " \')";
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

    public static void deleteItem(int id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from PostTreatment where ID == \'" + id + "\'";
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

