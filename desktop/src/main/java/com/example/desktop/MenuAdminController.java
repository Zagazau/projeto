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
    private Button pagarEncomendasButton;

    @FXML
    private Button sairButton;

    private static final Logger LOGGER = Logger.getLogger(MenuAdminController.class.getName());

    @FXML
    public void initialize() {
        consultarFaturacaoButton.setOnAction(this::consultarFaturacao);
        consultarProducaoButton.setOnAction(this::consultarProducao);
        controlarStockButton.setOnAction(this::controlarStock);
        encomendarLeiteButton.setOnAction(this::encomendarLeite);
        pagarEncomendasButton.setOnAction(event -> openScene("/com/example/desktop/Admin/PagarEncomendas.fxml", "Pagar Encomendas"));
        sairButton.setOnAction(event -> sair());
    }

    @FXML
    private void sair() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) sairButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Página Anterior");
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao voltar para a página anterior", e);
        }
    }

    @FXML
    private void encomendarLeite(ActionEvent event) {
        System.out.println("Botão Encomendar Leite pressionado!");
        openScene("/com/example/desktop/Admin/encomendasAdmin.fxml", "Encomendar Leite");
    }

    @FXML
    private void controlarStock(ActionEvent event) {
        System.out.println("Botão controlar stock pressionado!");
        openScene("/com/example/desktop/Admin/controlarStockAdmin.fxml", "Controlar Stock");
    }

    @FXML
    private void consultarFaturacao(ActionEvent event) {
        System.out.println("Botão consultar faturação pressionado!");
        openScene("/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml", "Consulta Faturação");
    }

    @FXML
    private void consultarProducao(ActionEvent event) {
        openScene("/com/example/desktop/Admin/ConsultarProducao.fxml", "Consultar Produção");
    }

    private void openScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) sairButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao abrir a cena: " + fxmlPath, e);
        }
    }
}
