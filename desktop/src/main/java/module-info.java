module com.example.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires dados;
    requires java.sql;

    exports com.example.desktop.clientes;
    exports com.example.desktop.Fornecedor;
    exports com.example.desktop.Admin to javafx.fxml;
    exports com.example.desktop;

    opens com.example.desktop to javafx.fxml;
    opens com.example.desktop.clientes to javafx.fxml;
    opens com.example.desktop.Admin to javafx.fxml;
    opens com.example.desktop.Fornecedor to javafx.fxml; // Adicione esta linha


}