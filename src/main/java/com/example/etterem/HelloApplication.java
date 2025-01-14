package com.example.etterem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    private String selectedOrder;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 500);
        stage.setTitle("Étterem!");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String view) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(view));
        stg.getScene().setRoot(pane);
    }

    public void changeScene(String view, String pass) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        Parent root = loader.load();
        OrderController c = loader.getController();

        c.initData(pass);

        stg.setScene(new Scene(root));
    }

    public String getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(String selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public static void main(String[] args) {
        launch();
    }
}