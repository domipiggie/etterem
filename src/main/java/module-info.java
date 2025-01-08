module com.example.etterem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.etterem to javafx.fxml;
    exports com.example.etterem;
}