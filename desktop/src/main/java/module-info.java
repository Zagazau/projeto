module com.example.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires dados;
    requires java.sql;


    opens com.example.desktop to javafx.fxml;
    exports com.example.desktop;
    opens com.example.desktop.clientes to javafx.fxml;
    exports com.example.desktop.clientes;
}