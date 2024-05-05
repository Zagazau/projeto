package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuAdminController {

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button goBack;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private Button sairButton;

    private static final Logger LOGGER = Logger.getLogger(MenuAdminController.class.getName());

    @FXML
    private void initialize() {
        consultarFaturacaoButton.setOnAction(this::consultarFaturacao);
        consultarProducaoButton.setOnAction(this::consultarProducao);
        controlarStockButton.setOnAction(this::controlarStock);
        encomendarLeiteButton.setOnAction(this::encomendarLeite);
        pagarEncomendasButton.setOnAction(this::pagarEncomendas);
        sairButton.setOnAction(this::goBack);
    }

    private void consultarFaturacao(ActionEvent event) {
        openScene("/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml", "Consulta Faturação", event);
    }

    private void consultarProducao(ActionEvent event) {
        openScene("/com/example/desktop/Admin/ConsultarProducao.fxml", "Consultar Produção", event);
    }

    private void controlarStock(ActionEvent event) {
        openScene("/com/example/desktop/Admin/controlarStockAdmin.fxml", "Controlar Stock", event);
    }

    private void encomendarLeite(ActionEvent event) {
        openScene("/com/example/desktop/Admin/encomendasAdmin.fxml", "Encomendar Leite", event);
    }

    private void pagarEncomendas(ActionEvent event) {
        openScene("/com/example/desktop/Admin/PagarEncomendas.fxml", "Pagar Encomendas", event);
    }

    private void goBack(ActionEvent event) {
        openScene("/com/example/desktop/PaginaAnterior.fxml", "Página Anterior", event);
    }

    private void openScene(String fxmlPath, String title, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao abrir a cena: " + fxmlPath, e);
        }
    }
}
