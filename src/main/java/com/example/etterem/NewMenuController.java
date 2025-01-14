package com.example.etterem;

import Classes.DatabaseManager;
import Classes.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewMenuController {
    public DatabaseManager dbManager = new DatabaseManager();

    @FXML
    public TextField itemName;

    @FXML
    public TextField itemPrice;


    public void onAddItemClick() throws IOException {
        dbManager.addMenuItem(new MenuItem(null, Integer.parseInt(itemPrice.getText()), itemName.getText()));

        HelloApplication main = new HelloApplication();
        main.changeScene("menu.fxml");
    }

    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("menu.fxml");
    }
}