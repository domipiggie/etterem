package Classes;

import Classes.MenuItem;

import java.util.ArrayList;

public class Order {
    private String id;
    private String tableNumber;
    private ArrayList<MenuItem> orderedItems;
    private int status;

    public Order(String id, String tableNumber, int status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.orderedItems = new ArrayList<>();
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

    public int getStatus() {
        return status;
    }

    public String toString(){
        return this.getId();
    }

    public void addItemToOrder(MenuItem mi) {
        this.orderedItems.add(mi);
    }
}