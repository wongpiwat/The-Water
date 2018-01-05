package databases;

import models.Standard;

import java.sql.*;

public class StandardDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static Standard getStandard() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                Standard standard = null;
                String query = "select * from Standard , Accounts where Standard.Account == Accounts.Username";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int no = resultSet.getInt("No");
                    String release = resultSet.getString("Release");
                    double temperature = resultSet.getDouble("Temperature");
                    double pH = resultSet.getDouble("pH");
                    double dissolvedOxygen = resultSet.getDouble("DissolvedOxygen");
                    double mlss = resultSet.getDouble("MLSS");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String date = resultSet.getString("Date");
                    String[] dateStrings = date.split(" ");
                    standard = new Standard(no, release, temperature, pH, dissolvedOxygen, mlss,firstName+" "+lastName,dateStrings[0]);
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

    public static void saveStandard(int no, String release, double temperature, double pH, double dissolvedOxygen, double mlss, String name, String date) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Standard set  No='" + no + "', Release='" + release + "' , Temperature='" + temperature + "', pH='" + pH + "' , DissolvedOxygen='" + dissolvedOxygen + "' , MLSS='" + mlss + "' , Account='" + name + "' , Date='" + date + "'";
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
