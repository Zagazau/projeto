package com.example.desktop.Fornecedor;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class inserirProdutosController {

    @FXML
    private Button adicionarProdutoButton;

    @FXML
    private Button backButton;

    @FXML
    private Button consultarEncomendasButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button inserirProdutosButton;

    @FXML
    private TextField nomeProdutoField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private Button sairButton;

    @FXML
    private TextField valorField;

    private ProdutoBll produtoBll;

    @FXML
    public void initialize() {
        produtoBll = new ProdutoBll();

        sairButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Saída");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja sair?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Página Anterior");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para o menu inicial.");
                }
            }
        });

        backButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/FornecedorMenu.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Menu Fornecedor");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
            }
        });

        consultarEncomendasButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarEncomendasFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Encomendas");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível ir para a página 'Consultar Encomendas'.");
            }
        });

        consultarFaturacaoButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarFaturacaoFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Faturação");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível ir para a página 'Consultar Faturação'.");
            }
        });

        adicionarProdutoButton.setOnAction(event -> {
            try {
                String nome = nomeProdutoField.getText();
                Float valor = Float.parseFloat(valorField.getText());
                Integer quantidade = Integer.parseInt(quantidadeField.getText());
                Integer idProduto = (int) (Math.random() * 10000);

                produtoBll.adicionarProduto(idProduto, nome, valor, quantidade, "fornecedor");
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto adicionado com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível adicionar o produto.");
            }
        });

        inserirProdutosButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/inserirProdutos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Inserir Produtos");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível ir para a página 'Inserir Produtos'.");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
