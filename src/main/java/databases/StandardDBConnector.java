package databases;

import models.Standard;

import java.sql.*;

public class StandardDBConnector {
    public static String dbURL = "jdbc:sqlite:Database.db";
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
                    double ph = resultSet.getDouble(1);
                    double bod = resultSet.getDouble(2);
                    double sulfide = resultSet.getDouble(3);
                    double settleableSolids = resultSet.getDouble(4);
                    double totalDissolvedSolid = resultSet.getDouble(5);
                    double suspendedSoilds = resultSet.getDouble(6);
                    double fatOilGrease = resultSet.getDouble(7);
                    double totalKjeldahlNitrogen = resultSet.getDouble(8);
                    standard = new Standard(ph, bod, sulfide, settleableSolids, totalDissolvedSolid, suspendedSoilds,fatOilGrease,totalKjeldahlNitrogen);
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

    public static void saveStandard(double ph, double bod, double sulfide, double settleableSolids, double totalDissolvedSolid, double suspendedSoilds,double fatOilGrease,double totalKjeldahlNitrogen) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Standard (pH, BOD, sulfide, settleableSolids, totalDissolvedSolid, suspendedSoilds, fatOilGrease, totalKjeldahlNitrogen) values (\'" + ph + "\', \'" + bod + "\' , \'" + sulfide + "\' , \'" + settleableSolids + "\' , \'" + totalDissolvedSolid + "\' , \'" + suspendedSoilds + "\' , \'" + fatOilGrease + "\' , \'" + totalKjeldahlNitrogen + "\')";
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
