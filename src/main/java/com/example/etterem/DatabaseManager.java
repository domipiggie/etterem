package com.example.etterem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/etterem"; // Replace 'localhost', '3306' and 'mydatabase' with your details
    private static final String username = "username";  // Your MySQL username
    private static final String password = "password";  // Your MySQL password

    private Connection createDatabaseConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

            connection.close();
            statement.close();

            return items;
        } catch (SQLException e) {
            return null;
        }
    }

    
}