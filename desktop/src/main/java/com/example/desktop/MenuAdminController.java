package com.example.desktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    public void initialize() {
        System.out.println("Inicializando MenuAdminController...");
        consultarFaturacaoButton.setOnAction(this::consultarFaturacao);
        consultarProducaoButton.setOnAction(this::consultarProducao);
        controlarStockButton.setOnAction(this::controlarStock);
        encomendarLeiteButton.setOnAction(this::encomendarLeite);
        pagarEncomendasButton.setOnAction(event -> {
            System.out.println("Botão Pagar Encomendas pressionado!");
            openScene("/com/example/desktop/Admin/PagarEncomendas.fxml", "Pagar Encomendas");
        });
        sairButton.setOnAction(this::sair);
    }

    @FXML
    private void sair(ActionEvent event) {
        System.out.println("Botão Sair pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Página Anterior");
            stage.show();
            System.out.println("Redirecionado para firstPage.fxml com sucesso");
        } catch (IOException e) {
            System.err.println("Erro ao carregar firstPage.fxml: " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
        }
    }

    @FXML
    private void encomendarLeite(ActionEvent event) {
        System.out.println("Botão Encomendar Leite pressionado!");
        openScene("/com/example/desktop/Admin/encomendasAdmin.fxml", "Encomendar Leite");
    }

    @FXML
    private void controlarStock(ActionEvent event) {
        System.out.println("Botão Controlar Stock pressionado!");

        openScene("/desktop/src/main/java/resources/com/example/desktop/Admin/controlarStockAdmin.fxml", "Controlar Stock");
    }

    @FXML
    private void consultarFaturacao(ActionEvent event) {
        System.out.println("Botão Consultar Faturação pressionado!");
        openScene("/com/example/desktop/Admin/consultarFaturacaoAdmin.fxml", "Consulta Faturação");
    }

    @FXML
    private void consultarProducao(ActionEvent event) {
        System.out.println("Botão Consultar Produção pressionado!");
        openScene("/com/example/desktop/Admin/ConsultarProducao.fxml", "Consultar Produção");
    }

    private void openScene(String fxmlPath, String title) {
        System.out.println("Tentando abrir a cena: " + fxmlPath);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) sairButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
            System.out.println("Cena " + fxmlPath + " carregada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar " + fxmlPath + ": " + e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: " + fxmlPath);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        System.out.println("Mostrando alerta: " + title + " - " + message);
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
