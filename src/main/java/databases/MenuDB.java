package databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;

import java.sql.*;

public class MenuDB {

    public ObservableList loadMenu() {
        ObservableList<Menu> menu = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Menu.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                String query = "select * from Menu";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String typeFood = resultSet.getString(2);
                    String nameFood = resultSet.getString(3);
                    Double price = resultSet.getDouble(4);
                    menu.add(new Menu(id, typeFood, nameFood, price));
                }
                //close connection
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return menu;
    }

    public void saveDB(int id, String typeFood, String nameFood, Double price) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Menu.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "insert into Menu(id, Type, Name_Food, Price) values (\'" + id + "\', \'" + typeFood + "\' , \'" + nameFood + "\',\'" + price + "')";
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

    public void deleteDB(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Menu.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Delete from Menu where id == \'" + id + "\'";
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

    public int getCreateID() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Menu.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "Select max(id) from Menu";
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

    public void editDB(int id, String typeFood, String nameFood, double price) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Menu.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update Menu set id=\'" + id + "\' ,Type=\'" + typeFood + "\' ,Name_Food=\'" + nameFood + "\' ,Price=\'" + price + "\' where ID == \'" + id + "\'";
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

