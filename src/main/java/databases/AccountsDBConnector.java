package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Account;

import java.sql.*;

public class AccountsDBConnector {
    private static String dbURL = "jdbc:sqlite:Database.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList loadAccountsToTable() {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select * from Accounts";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int no = resultSet.getInt("No");
                    String type = resultSet.getString("Type");
                    String department = resultSet.getString("Department");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    accounts.add(new Account(no,type, department, firstName, lastName, username, password));
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

    public static void saveAccount(String type, String department, String firstname, String lastname, String username, String password) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Accounts (Type, Department, FirstName, LastName, Username, Password) values (\'" + type + "\' , \'" + department + "\' , \'" + firstname + "\' , \'" + lastname + "\' , \'" + username + "\' , \'" + password + "')";
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
                String query = "Delete from Accounts where Username == \'" + username + "\'";
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
                String query = "Select * from Accounts where Username=='"+username+"' and Password=='"+password+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int no = resultSet.getInt("No");
                String type = resultSet.getString("Type");
                String department = resultSet.getString("Department");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String userName = resultSet.getString("Username");
                String passWord = resultSet.getString("Password");
                connection.close();
                return new Account(no,type,department,firstName,lastName,userName,passWord);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkUser(String username ,String password) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select Username from Accounts where Username=='"+username+"' and Password=='"+password+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                String userName = resultSet.getString("Username");
                connection.close();
                if (username.equals(userName)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
