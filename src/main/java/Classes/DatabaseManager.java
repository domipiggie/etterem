package Classes;

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
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            closeDatabaseConnection(connection,statement);
            System.out.println("inserted new item");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Order> getOrders(){
        try {
            Connection connection = createDatabaseConnection();

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM orders";
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                String orderId = resultSet.getString("orderId");
                String tableName = resultSet.getString("tableName");
                int status = resultSet.getInt("status");

                orders.add(new Order(orderId, tableName, status));
            }

            closeDatabaseConnection(connection, statement);

            connection = createDatabaseConnection();
            statement = connection.createStatement();


            for (int i = 0; i< orders.size(); i++){
                query = "SELECT `menu_item`.* FROM `menu_item` INNER JOIN `ordered_items` ON `menu_item`.`itemId` = `ordered_items`.`itemId` WHERE `ordered_items`.`orderId` = "+orders.get(i).getId()+";";
                resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    String itemId = resultSet.getString("itemId");
                    int price = resultSet.getInt("price");
                    String name = resultSet.getString("name");

                    orders.get(i).addItemToOrder(new MenuItem(itemId, price, name));
                }
            }

            System.out.println("fetched orders");
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrderStatus(String id, String status){
        try{
            Connection connection = createDatabaseConnection();
            Statement statement = connection.createStatement();

            String query = "UPDATE orders SET status = "+status+" WHERE orderId = "+id+";";

            statement.executeUpdate(query);

            closeDatabaseConnection(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrder(Order o){
        try{
            Connection connection = createDatabaseConnection();
            Statement statement = connection.createStatement();

            String query = "INSERT INTO orders (tableName,status) VALUES (\""+o.getTableNumber()+"\", "+o.getStatus()+");";
            statement.executeUpdate(query);

            query = "SELECT orderId FROM orders WHERE tableName = \""+o.getTableNumber()+"\" AND status = "+o.getStatus()+" ORDER BY orderId DESC";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            String orderId = resultSet.getString("orderId");

            for (MenuItem mi:o.getOrderedItems()){
                query = "INSERT INTO ordered_items (orderId, itemId) VALUES ("+orderId+", "+mi.getId()+");";
                statement.executeUpdate(query);
            }

            closeDatabaseConnection(connection,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrder(String id){
        try{
            Connection connection = createDatabaseConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM orders WHERE orderId = "+id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Order o = new Order(resultSet.getString("orderId"),resultSet.getString("tableName"),resultSet.getInt("status"));
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}