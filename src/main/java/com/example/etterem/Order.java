package com.example.etterem;

import java.util.ArrayList;

public class Order {
    private String id;
    private String tableNumber;
    private ArrayList<MenuItem> orderedItems;
    private String status;

    public Order(String id, String tableNumber, ArrayList<MenuItem> orderedItems, String status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.orderedItems = orderedItems;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public ArrayList<MenuItem> getOrderedItems() {
        return orderedItems;
    }

    public String getStatus() {
        return status;
    }
}