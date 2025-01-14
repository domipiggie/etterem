package com.example.etterem;

import Classes.DatabaseManager;
import Classes.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {
    @FXML
    public TableView ordersTable;
    private DatabaseManager dbManager = new DatabaseManager();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

    public void redraw(){
        ordersTable.getItems().clear();

        ArrayList<Order> dbOrders = dbManager.getOrders();

        for (Order v: dbOrders){
            ordersTable.getItems().add(v);
        }
    }

    public void onViewOrder() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("order.fxml", ordersTable.getSelectionModel().getSelectedItem().toString());
    }

    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");
    }

    public void onDeleteOrder() {
        dbManager.removeOrder(ordersTable.getSelectionModel().getSelectedItem().toString());
        redraw();
    }
}
