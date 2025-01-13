package com.example.etterem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/etterem";
    private static final String username = "username";
    private static final String password = "password";

    private Connection createDatabaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("connected to db");
        return connection;
    }

    private void closeDatabaseConnection(Connection c, Statement s) throws SQLException {
        System.out.println("dropping db connection");
        c.close();
        s.close();
    }

    public ArrayList<MenuItem> getMenuItems() {
        try {
            Connection connection = createDatabaseConnection();

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM menu_item";
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<MenuItem> items = new ArrayList<>();

            while (resultSet.next()) {
                String itemId = resultSet.getString("itemId");
                int price = resultSet.getInt("price");
                String name = resultSet.getString("name");

                items.add(new MenuItem(itemId, price, name));
            }

            closeDatabaseConnection(connection, statement);

            System.out.println("fetched menu items");
            return items;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public void addMenuItem(MenuItem mi){
        try {
            Connection connection = createDatabaseConnection();

            String query = "INSERT INTO menu_item (price, name) VALUES ("+mi.getPrice()+", \""+mi.getName()+"\")";
            System.out.println(query);
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            closeDatabaseConnection(connection,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}