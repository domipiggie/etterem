package com.example.etterem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public DatabaseManager dbManager = new DatabaseManager();
    @FXML
    public TextField tableNumber;
    @FXML
    private TableView menuListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize menu view
        menuListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        menuListView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ArrayList<MenuItem> dbOrders = dbManager.getMenuItems();

        TableColumn idColumn = new TableColumn("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn asztalColumn = new TableColumn("Név");
        asztalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn statusColumn = new TableColumn("Ár");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuListView.getColumns().addAll(idColumn, asztalColumn, statusColumn);

        for (MenuItem v: dbOrders){
            menuListView.getItems().add(v);
        }
    }

    public void onPlaceOrderClick() throws IOException {
        ObservableList<MenuItem> items = menuListView.getSelectionModel().getSelectedItems();
        System.out.println(tableNumber.getText());
        Order order = new Order("-100", tableNumber.getText(), 0);

        for (MenuItem mi:items){
            order.addItemToOrder(mi);
        }

        dbManager.addOrder(order);
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");
    }

    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");

    }
}