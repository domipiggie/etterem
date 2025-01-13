package com.example.etterem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public DatabaseManager dbManager = new DatabaseManager();
    @FXML
    public Button editMenuButton;

    @FXML
    private ListView menuListView;

    @FXML
    protected void onEditMenuButtonClick() throws Exception{
        HelloApplication main = new HelloApplication();
        main.changeScene("newMenuItem.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ArrayList<MenuItem> dbResult = dbManager.getMenuItems();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (MenuItem v:dbResult){
            items.add(v.toString());
        }

        menuListView.setItems(items);

        menuListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle the selected item here
            System.out.println("Selected item: " + newValue);
        });
    }
}