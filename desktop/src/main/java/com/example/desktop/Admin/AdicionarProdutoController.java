package com.example.desktop.Admin;

import BLL.ProdutoBll;
import entity.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdicionarProdutoController {

    @FXML
    private Button adicionarProdutoButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private TextField nomeProdutoField;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TextField quantidadeField;

    @FXML
    private Button sairButton;

    @FXML
    private TextField valorField;

    @FXML
    private Button voltarButton;

    private ProdutoBll produtoBll;

    @FXML
    public void initialize() {
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));

        produtoBll = new ProdutoBll();

        adicionarProdutoButton.setOnAction(event -> adicionarProduto());
    }

    private void adicionarProduto() {
        String nome = nomeProdutoField.getText();
        String quantidadeText = quantidadeField.getText();
        String valorText = valorField.getText();

        if (nome.isEmpty() || quantidadeText.isEmpty() || valorText.isEmpty()) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            int quantidade = Integer.parseInt(quantidadeText);
            float valor = Float.parseFloat(valorText);

            Produto produto = new Produto(nome, valor, quantidade);
            produtoBll.adicionarProduto(produto);
            showAlert("Sucesso", "Produto adicionado com sucesso!");

            // Limpar os campos após adicionar
            nomeProdutoField.clear();
            quantidadeField.clear();
            valorField.clear();
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade e valor devem ser numéricos.");
        }
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
