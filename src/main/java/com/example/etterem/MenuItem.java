package com.example.etterem;

public class MenuItem {
    private String id;
    private int price;
    private String name;

    public MenuItem(String id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return  this.id;
    }
}