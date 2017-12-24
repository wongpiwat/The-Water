package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Treatment;

import java.sql.*;
import java.text.DecimalFormat;

public class TreatmentsDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static ObservableList getPreTreatments() {
        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from PreTreatment , Treatment, Accounts where PreTreatment.TreatmentID == Treatment.TreatmentID and Treatment.Account == Accounts.Username";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String date = resultSet.getString("DateWater");
                    double volumeWater = resultSet.getDouble("VolumeWater");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double mlss = resultSet.getDouble("MLSS");
                    String dateForm = resultSet.getString("DateForm");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    treatments.add(new Treatment(id, date, decimalFormat.format(volumeWater), decimalFormat.format(temperature), decimalFormat.format(pH),decimalFormat.format(dissolvedOxygen),decimalFormat.format(mlss),dateForm,firstName+" "+lastName.subSequence(0,1)));
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

    public static ObservableList getPostTreatments() {
        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from PostTreatment, Treatment, Accounts where PostTreatment.TreatmentID == Treatment.TreatmentID and Treatment.Account == Accounts.Username";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String date = resultSet.getString("DateWater");
                    double volumeWater = resultSet.getDouble("VolumeWater");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double volumeSediment = resultSet.getDouble("VolumeSediment");
                    double mlss = resultSet.getDouble("MLSS");
                    double electricity = resultSet.getDouble("Electricity");
                    double deodorizerSystem = resultSet.getDouble("DeodorizerSystem");
                    String standard = resultSet.getString("Standard");
                    String dateForm = resultSet.getString("DateForm");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    treatments.add(new Treatment(id, date, decimalFormat.format(volumeWater), decimalFormat.format(temperature), decimalFormat.format(pH),decimalFormat.format(dissolvedOxygen),decimalFormat.format(volumeSediment),decimalFormat.format(mlss),decimalFormat.format(electricity),decimalFormat.format(deodorizerSystem),standard,dateForm,firstName+" "+lastName.subSequence(0,1)));
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

    public static void savePreTreatment(String Date, double volumeWater,double temperature,double pH,double dissolvedOxygen,double mlss,String dateForm,String account) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Treatment (DateWater, VolumeWater, Temperature, pH, DissolvedOxygen, MLSS, DateForm, Account) values ('" + Date + "', '" + volumeWater + "', '" + temperature + "', '" + pH + "', '" + dissolvedOxygen + "' , '" + mlss + "', '" +dateForm+ "' , '"+account+"')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "select max(TreatmentID) from Treatment";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int max = resultSet.getInt(1);
                query = "insert into PreTreatment (TreatmentID) values ("+max+")";
                p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePostTreatment(String Date, double volumeWater,double temperature,double pH,double dissolvedOxygen,double volumeSediment,double mlss,double electricity,double deodorizerSystem,String standard,String dateForm,String account) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Treatment (DateWater, VolumeWater, Temperature, pH, DissolvedOxygen, MLSS, DateForm, Account) values ('" + Date + "', '" + volumeWater + " ', '" + temperature + " ', ' " + pH + " ', ' " + dissolvedOxygen + " ' , ' " + mlss + " ', '" +dateForm+ "' , '"+account+"')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "select max(TreatmentID) from Treatment";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int max = resultSet.getInt(1);
                query = "insert into PostTreatment (TreatmentID, VolumeSediment, Electricity, DeodorizerSystem, Standard) values ("+max+","+volumeSediment+","+electricity+","+deodorizerSystem+",'"+standard+"')";
                p = connection.prepareStatement(query);
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
                String query = "select TreatmentID from PreTreatment";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int treatmentID = resultSet.getInt(1);
                query = "Delete from Treatment where TreatmentID == '" + treatmentID + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "Delete from PreTreatment where ID == '" + id + "'";
                p = connection.prepareStatement(query);
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
                String query = "select TreatmentID from PostTreatment";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int treatmentID = resultSet.getInt(1);
                query = "Delete from Treatment where TreatmentID == '" + treatmentID + "'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "Delete from PostTreatment where ID == '" + id + "'";
                p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllTreatments() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Treatment";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "Delete from PreTreatment";
                p = connection.prepareStatement(query);
                p.executeUpdate();
                query = "Delete from PostTreatment";
                p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetSequence() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "UPDATE sqlite_sequence SET seq = 0 where name = 'PreTreatment' or name = 'PostTreatment' or name = 'Treatment'";
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
}
