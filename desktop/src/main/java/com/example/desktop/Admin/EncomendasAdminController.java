package com.example.desktop.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EncomendasAdminController {

    @FXML
    private Button cancelarEncomendasButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button encomendarButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private Button sairButton;

    @FXML
    private Button voltarButton;

    @FXML
    private void initialize() {
        cancelarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/cancelarEncomenda.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        encomendarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/efetuarEncomendasAdmin.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendarLeite.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendas.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/adminMenu.fxml"));
    }

    private void loadScene(javafx.event.ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao carregar a página: " + fxmlFile);
        }
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
