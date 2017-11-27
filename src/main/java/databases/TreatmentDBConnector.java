package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Treatment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDBConnector {
    public static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList loadPostTreatmentToTable() {
        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Treatment where type = 'post'";
                Statement statement = connection.createStatement();
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
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }

    public static ObservableList loadPreTreatmentToTable() {
        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Treatment where type = 'pre'";
                System.out.println(query);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String date = resultSet.getString("Date");
                    double volumeWater = resultSet.getDouble("VolumeWater");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double mlss = resultSet.getDouble("MLSS");
                    treatments.add(new Treatment(id, date, volumeWater, temperature, pH,dissolvedOxygen,mlss));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }

    public static void savePostTreatment(String Date, double volumeWater,double temperature,double pH,double dissolvedOxygen,double volumeSediment,double mlss,double electricity,double deodorizerSystem) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Treatment (Type, Date, VolumeWater, Temperature, pH, DissolvedOxygen, VolumeSediment, MLSS, Electricity, DeodorizerSystem) values (\'post\' , \'" + Date + "\', \'" + volumeWater + " \', \'" + temperature + " \', \' " + pH + " \', \' " + dissolvedOxygen + " \' , \' "+ volumeSediment + "\' , \' " + mlss + " \' , \' " + electricity + " \' , \' " + deodorizerSystem + " \')";
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

    public static void savePreTreatment(String Date, double volumeWater,double temperature,double pH,double dissolvedOxygen,double mlss) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Treatment (Type, Date, VolumeWater, Temperature, pH, DissolvedOxygen, MLSS) values (\'pre\' , \'" + Date + "\', \'" + volumeWater + " \', \'" + temperature + " \', \' " + pH + " \', \' " + dissolvedOxygen + " \' , \' " + mlss + " \')";
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

    public static void deletePostTreatment(int id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Treatment where type == 'post' and ID == \'" + id + "\'";
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

    public static void deletePreTreatment(int id) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Treatment where type == 'pre' and ID == \'" + id + "\'";
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

    public static List getAllPreTreatment() {
        List<Treatment> treatments = new ArrayList<>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Treatment where type = 'pre'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String date = resultSet.getString("Date");
                    double volumeWater = resultSet.getDouble("VolumeWater");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double mlss = resultSet.getDouble("MLSS");
                    treatments.add(new Treatment(id, date, volumeWater, temperature, pH,dissolvedOxygen,mlss));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }


    public static List getAllPostTreatment() {
        List<Treatment> treatments = new ArrayList<>();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Treatment where type = 'post'";
                Statement statement = connection.createStatement();
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
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }
}

