package com.example.etterem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderController {
    private DatabaseManager dbManager = new DatabaseManager();
    private String id;
    @FXML
    public ListView menuListView;
    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");
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

        for (MenuItem v:o.getOrderedItems()){
            items.add(v.toString());
        }

        menuListView.setItems(items);
    }

    public void markAsCompletedClick() {
        dbManager.updateOrderStatus(id, "1");
    }
}
