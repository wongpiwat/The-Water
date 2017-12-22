package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;

import java.sql.*;

public class EventLogDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList loadEventLogToTable() {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from EventLog";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String type = resultSet.getString("Type");
                    String department = resultSet.getString("Department");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    accounts.add(new Account(type, department, firstName, lastName, username, password));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }
}
