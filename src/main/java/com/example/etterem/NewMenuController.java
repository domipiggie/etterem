package com.example.etterem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewMenuController {
    public DatabaseManager dbManager = new DatabaseManager();

    @FXML
    public TextField itemName;

    @FXML
    public TextField itemPrice;


    public void onAddItemClick() throws IOException {
        dbManager.addMenuItem(new MenuItem(null, Integer.parseInt(itemPrice.getText()), itemName.getText()));

        HelloApplication main = new HelloApplication();
        main.changeScene("rendeles.fxml");
    }
}