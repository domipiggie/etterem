package com.example.etterem;

import Classes.DatabaseManager;
import Classes.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public DatabaseManager dbManager = new DatabaseManager();
    @FXML
    private ListView menuListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize menu view
        ArrayList<Classes.MenuItem> dbItems = dbManager.getMenuItems();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (MenuItem v:dbItems){
            items.add(v.toString());
        }

        menuListView.setItems(items);
    }

    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");
    }

    public void addItem() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("newMenuItem.fxml");
    }
}