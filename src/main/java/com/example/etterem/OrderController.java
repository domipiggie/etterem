package com.example.etterem;

import Classes.DatabaseManager;
import Classes.MenuItem;
import Classes.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class OrderController {
    @FXML
    public Label table;
    @FXML
    public Label status;
    @FXML
    public Label priceText;
    private DatabaseManager dbManager = new DatabaseManager();
    private String id;
    @FXML
    public ListView menuListView;
    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("orderList.fxml");
    }

    public void initData(String s){
        System.out.println(s);
        id = s;
        ArrayList<Order> orders = dbManager.getOrders();
        Order o = null;
        int i = 0;
        while (o==null && i < orders.size()){
            if (Objects.equals(orders.get(i).getId(), s)) {
                o = orders.get(i);
            }
            i++;
        }

        ObservableList<String> items = FXCollections.observableArrayList();

        int price = 0;
        for (MenuItem v:o.getOrderedItems()){
            items.add(v.toString());
            price+=v.getPrice();
        }

        menuListView.setItems(items);

        o = dbManager.getOrder(s);

        priceText.setText("Összesített ár: "+Integer.toString(price));
        table.setText("Asztal: "+o.getTableNumber());
        status.setText("Státusz: "+o.getStatus());
    }

    public void markAsCompletedClick() {
        dbManager.updateOrderStatus(id, "1");
    }
}
