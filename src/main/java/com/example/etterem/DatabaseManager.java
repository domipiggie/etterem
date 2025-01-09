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

    private Connection createDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeDatabaseConnection(Connection c, Statement s) throws SQLException {
        c.close();
        s.close();
    }

    public ArrayList<MenuItem> getMenuItems() {
        Connection connection = createDatabaseConnection();

        try {
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

            return items;
        } catch (SQLException e) {
            return null;
        }
    }

    public void addMenuItem(MenuItem mi){
        Connection connection = createDatabaseConnection();

        try {
            String query = "INSERT INTO menu_item (price, name) VALUES ("+mi.getPrice()+mi.getName()+")";
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            closeDatabaseConnection(connection,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}