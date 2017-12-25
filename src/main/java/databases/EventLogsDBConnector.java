package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Log;

import java.sql.*;

public class EventLogsDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList getEventLogs() {
        ObservableList<Log> logs = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from EventLogs, Accounts where EventLogs.Account == Accounts.Username";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String no = resultSet.getString("No");
                    String date = resultSet.getString("Date");
                    String logType = resultSet.getString("LogType");
                    String accountType = resultSet.getString("Type");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String event = resultSet.getString("Event");
                    String source = resultSet.getString("Source");
                    logs.add(new Log(no, date, logType, accountType, firstName+" "+lastName.subSequence(0,1), event, source));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return logs;
    }

    public static void saveLog(String date, String type, String username, String event, String source) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into EventLogs (Date, LogType, Account, Event, Source) values ('" + date + "' , '" + type + "' , '" + username + "' , '" + event + "' , '"+ source +"')";
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

    public static void deleteAllLogs() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from EventLogs";
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

    public static void resetSequence() {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "UPDATE sqlite_sequence  SET seq = 0 where name = 'EventLogs'";
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
