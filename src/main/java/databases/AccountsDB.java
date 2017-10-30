package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Accounts;

import java.sql.*;

public class AccountsDB {

    public static ObservableList loadAccounts() {
        ObservableList<Accounts> accounts = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Accounts.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Accounts";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String department = resultSet.getString(2);
                    String firstName = resultSet.getString(3);
                    String lastName = resultSet.getString(4);
                    String username = resultSet.getString(5);
                    String password = resultSet.getString(6);
                    accounts.add(new Accounts(id, department, firstName, lastName, username, password));
                }
                //close connection
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accounts;
    }

    public static void saveAccountsDB(int id, String department, String firstname, String lastname, String username, String password) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Accounts.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Accounts (id, department, firstName, lastName, userName, password) values (\'" + id + "\', \'" + department + "\' , \'" + firstname + "\' , \'" + lastname + "\' , \'" + username + "\' , \'" + password + "')";
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

    public static void deleteAccountsDB(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Accounts.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Accounts where id == \'" + id + "\'";
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

    public static int getCreateAccountsID() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Accounts.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select max(id) from Accounts";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int minID = resultSet.getInt(1);
                connection.close();
                return minID + 1;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static void editAccountsDB(int id, String department, String firstname, String lastname, String username, String password) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Accounts.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Accounts set id=\'" + id + "\' ,department=\'" + department + "\' ,firstname=\'" + firstname +
                        "\' ,lastname=\'" + lastname + "\' ,username=\'" + username + "\' ,password=\'" + password + "\' where ID == \'" + id + "\'";
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
}
