package databases;

import models.Standard;

import java.sql.*;

public class StandardDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static Standard loadStandardToTable() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                Standard standard = null;
                String query = "select * from Standard";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double mlss = resultSet.getDouble("MLSS");
                    standard = new Standard(temperature, pH, dissolvedOxygen, mlss);
                }
                connection.close();
                return standard;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void saveStandard(double temperature, double pH, double dissolvedOxygen, double mlss) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Standard (Temperature, pH, DissolvedOxygen, MLSS) values (\'" + temperature + "\', \'" + pH + "\' , \'" + dissolvedOxygen + "\' , \'" + mlss + "\')";
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

    public static void deleteStandard() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Standard";
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
