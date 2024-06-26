package com.example.desktop.Fornecedor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuFornecedorController {

    @FXML
    private Button consultarEncomendasButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button inserirProdutosButton;

    @FXML
    private Button sairButton;

    @FXML
    public void initialize() {
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
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a página 'Consultar Encomendas'.");
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
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a página 'Consultar Faturação'.");
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
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a página 'Inserir Produtos'.");
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
