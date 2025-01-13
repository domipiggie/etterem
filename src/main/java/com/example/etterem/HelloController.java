package com.example.etterem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public DatabaseManager dbManager = new DatabaseManager();
    @FXML
    public Button editMenuButton;
    @FXML
    public TableView ordersTable;
    @FXML
    private ListView menuListView;
    @FXML
    protected void onEditMenuButtonClick() throws Exception{
        HelloApplication main = new HelloApplication();
        main.changeScene("newMenuItem.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize menu view
        menuListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ArrayList<MenuItem> dbItems = dbManager.getMenuItems();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (MenuItem v:dbItems){
            items.add(v.toString());
        }

        menuListView.setItems(items);

        menuListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle the selected item here
            System.out.println("Selected item: " + newValue);
        });

        //Initialize order view
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<String> orders = FXCollections.observableArrayList();
        ArrayList<Order> dbOrders = dbManager.getOrders();

        TableColumn idColumn = new TableColumn("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn asztalColumn = new TableColumn("Asztal");
        asztalColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        TableColumn statusColumn = new TableColumn("Státusz");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ordersTable.getColumns().addAll(idColumn, asztalColumn, statusColumn);

        for (Order v: dbOrders){
            ordersTable.getItems().add(v);
        }
    }
}