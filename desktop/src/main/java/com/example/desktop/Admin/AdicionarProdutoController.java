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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        produtoBll = new ProdutoBll();


        adicionarProdutoButton.setOnAction(event -> {
            System.out.println("Botão Adicionar Produto pressionado!");
            try {
                String nome = nomeProdutoField.getText();
                Float valor = Float.parseFloat(valorField.getText());
                Integer quantidade = Integer.parseInt(quantidadeField.getText());
                Integer idProduto = (int) (Math.random() * 10000); // Gerando um ID aleatório para demonstração

                produtoBll.adicionarProduto(idProduto, nome, valor, quantidade, "admin");
                showAlert("Sucesso", "Produto adicionado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Erro", "Não foi possível adicionar o produto.");
            }
        });
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
