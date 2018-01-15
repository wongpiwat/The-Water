package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;

import java.sql.*;

public class AccountsDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList getAccounts() {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Accounts";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String type = resultSet.getString("Type");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    String status = resultSet.getString("Status");
                    accounts.add(new Account(type, firstName, lastName, username, password, status));
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

    public static void saveAccount(String type, String firstname, String lastname, String username, String password, String status) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Accounts (Type, FirstName, LastName, Username, Password, Status) values ('" + type + "' , '" + firstname + "' , '" + lastname + "' , '" + username + "' , '" + password + "' , '" + status + "')";
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

    public static void editAccount(String type, String firstname, String lastname, String username, String password) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Accounts set Type = '" + type + "' , FirstName = '" + firstname + "' , LastName = '" + lastname + "' , Username = '" + username + "' , Password = '" + password + "' where Accounts.Username =='" + username + "'";
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

    public static void blockAccount(String username, String status) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Accounts set Status = '" + status + "' where Accounts.Username =='" + username + "'";
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

    public static void deleteAccount(String username) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Accounts where Username == '" + username + "'";
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

    public static Account isLogin(String username,String password) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select * from Accounts where Username=='" + username + "' and Password=='" + password + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                String type = resultSet.getString("Type");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                String status = resultSet.getString("Status");
                connection.close();
                return new Account(type, firstName, lastName, userName, passWord, status);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
        } catch (SQLException e) {
            System.err.println("Account not found");
        }
        return null;
    }
}
