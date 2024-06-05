package com.example.desktop.Fornecedor;

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
    private TextArea descricaoField;

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

    @FXML
    public void initialize() {
        sairButton.setOnAction(event -> {
            System.out.println("Botão Sair pressionado!");
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
                    System.out.println("Redirecionado para firstPage.fxml com sucesso");
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
                }
            }
        });

        backButton.setOnAction(event -> {
            System.out.println("Botão Voltar pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/FornecedorMenu.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Menu Fornecedor");
                stage.show();
                System.out.println("Redirecionado para FornecedorMenu.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
            }
        });

        consultarEncomendasButton.setOnAction(event -> {
            System.out.println("Botão Consultar Encomendas pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarEncomendasFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Encomendas");
                stage.show();
                System.out.println("Redirecionado para consultarEncomendasFornecedor.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena consultarEncomendasFornecedor.fxml.");
            }
        });

        consultarFaturacaoButton.setOnAction(event -> {
            System.out.println("Botão Consultar Faturação pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarFaturacaoFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Faturação");
                stage.show();
                System.out.println("Redirecionado para consultarFaturacaoFornecedor.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena consultarFaturacao.fxml.");
            }
        });

        inserirProdutosButton.setOnAction(event -> {
            System.out.println("Botão Inserir Produtos pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/inserirProdutos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Inserir Produtos");
                stage.show();
                System.out.println("Redirecionado para inserirProdutos.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena inserirProdutos.fxml.");
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