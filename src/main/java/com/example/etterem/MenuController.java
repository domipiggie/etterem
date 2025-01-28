package com.example.etterem;

import Classes.DatabaseManager;
import Classes.MenuItem;
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

public class MenuController implements Initializable {
    public DatabaseManager dbManager = new DatabaseManager();
    @FXML
    private TableView menuListView;
    private ArrayList<Classes.MenuItem> dbOrders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize menu view
        menuListView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /*TableColumn idColumn = new TableColumn("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));*/

        TableColumn asztalColumn = new TableColumn("Név");
        asztalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn statusColumn = new TableColumn("Ár");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuListView.getColumns().addAll(/*idColumn, */asztalColumn, statusColumn);

        reloadTable();
    }

    public void reloadTable(){
        //Initialize menu view
        dbOrders = dbManager.getMenuItems();
        menuListView.getItems().clear();
        for (Classes.MenuItem v: dbOrders){
            menuListView.getItems().add(v);
        }
    }

    public void onBackToMainClick() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("mainMenu.fxml");
    }

    public void addItem() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("newMenuItem.fxml");
    }

    public void deleteItem() {
        System.out.println();
        dbManager.removeMenuItem(dbOrders.get(menuListView.getSelectionModel().getSelectedIndex()).getId());
        reloadTable();
    }
}