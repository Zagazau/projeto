package com.example.desktop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

        if (consultarFaturacaoButton != null) {
            System.out.println("Botão consultarFaturacaoButton inicializado.");
            consultarFaturacaoButton.setOnAction(this::consultarFaturacao);
        } else {
            System.out.println("Botão consultarFaturacaoButton é nulo.");
        }
        if (consultarProducaoButton != null) {
            System.out.println("Botão consultarProducaoButton inicializado.");
            consultarProducaoButton.setOnAction(this::consultarProducao);
        } else {
            System.out.println("Botão consultarProducaoButton é nulo.");
        }
        if (controlarStockButton != null) {
            System.out.println("Botão controlarStockButton inicializado.");
            controlarStockButton.setOnAction(this::controlarStock);
        } else {
            System.out.println("Botão controlarStockButton é nulo.");
        }
        if (encomendarLeiteButton != null) {
            System.out.println("Botão encomendarLeiteButton inicializado.");
            encomendarLeiteButton.setOnAction(this::encomendarLeite);
        } else {
            System.out.println("Botão encomendarLeiteButton é nulo.");
        }
        if (pagarEncomendasButton != null) {
            System.out.println("Botão pagarEncomendasButton inicializado.");
            pagarEncomendasButton.setOnAction(this::pagarEncomendas);
        } else {
            System.out.println("Botão pagarEncomendasButton é nulo.");
        }
        if (sairButton != null) {
            System.out.println("Botão sairButton inicializado.");
            sairButton.setOnAction(this::sair);
        } else {
            System.out.println("Botão sairButton é nulo.");
        }
    }

    @FXML
    private void sair(javafx.event.ActionEvent event) {
        System.out.println("Botão Sair pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Página Anterior");
            stage.show();
            System.out.println("Redirecionado para firstPage.fxml com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
        }
    }

    @FXML
    private void encomendarLeite(javafx.event.ActionEvent event) {
        System.out.println("Botão Encomendar Leite pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/encomendasAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Encomendar Leite");
            stage.show();
            System.out.println("Cena encomendasAdmin.fxml carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: encomendasAdmin.fxml");
        }
    }

    @FXML
    private void controlarStock(javafx.event.ActionEvent event) {
        System.out.println("Botão Controlar Stock pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/controlarStockAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Controlar Stock");
            stage.show();
            System.out.println("Cena controlarStockAdmin.fxml carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: controlarStockAdmin.fxml");
        }
    }

    @FXML
    private void consultarFaturacao(javafx.event.ActionEvent event) {
        System.out.println("Botão Consultar Faturação pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/consultarFaturacaoAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Consulta Faturação");
            stage.show();
            System.out.println("Cena consultarFaturacaoAdmin.fxml carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: consultarFaturacaoAdmin.fxml");
        }
    }

    @FXML
    private void consultarProducao(javafx.event.ActionEvent event) {
        System.out.println("Botão Consultar Produção pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/ConsultarProducao.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Consultar Produção");
            stage.show();
            System.out.println("Cena ConsultarProducao.fxml carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: ConsultarProducao.fxml");
        }
    }

    private void pagarEncomendas(javafx.event.ActionEvent event) {
        System.out.println("Botão Pagar Encomendas pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/PagarEncomendas.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Pagar Encomendas");
            stage.show();
            System.out.println("Cena PagarEncomendas.fxml carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: PagarEncomendas.fxml");
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
