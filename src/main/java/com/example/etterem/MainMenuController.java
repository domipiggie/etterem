package com.example.etterem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button createOrder;
    @FXML
    public Button viewOrders;
    @FXML
    public Button viewMenu;
    @FXML
    public Button addItem;

    @FXML
    protected void createOrderClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("rendeles.fxml");
    }

    @FXML
    protected void viewOrdersClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("orderList.fxml");
    }

    @FXML
    protected void viewMenuClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("menu.fxml");
    }

    @FXML
    protected void addItemClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("newMenuItem.fxml");
    }
}
