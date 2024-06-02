package com.example.desktop.clientes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class efetuarPedidosClienteController {

    @FXML
    private Button backButton;

    @FXML
    private Button consultarProdutosButton;

    @FXML
    private Button efetuarPedidoButton;

    @FXML
    private Button pagamentosButton;

    @FXML
    private Button pedidosButton;

    @FXML
    private TextField quantidadeField;

    @FXML
    private MenuButton queijoField;

    @FXML
    private Button sairButton;

    @FXML
    public void initialize() {
        sairButton.setOnAction(event -> {
            System.out.println("Botão Sair pressionado!");
            showConfirmationAlert(event);
        });

        pedidosButton.setOnAction(event -> {
            System.out.println("Botão Pedidos pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/efetuarPedido.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Efetuar Pedido");
                stage.show();
                System.out.println("Redirecionado para efetuarPedido.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena efetuarPedido.fxml.");
            }
        });

        pagamentosButton.setOnAction(event -> {
            System.out.println("Botão Pagamentos pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/pagarPedidos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Pagamentos");
                stage.show();
                System.out.println("Redirecionado para pagarPedidos.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena pagarPedidos.fxml.");
            }
        });

        consultarProdutosButton.setOnAction(event -> {
            System.out.println("Botão Consultar Produtos pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/consultarProdutos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Produtos");
                stage.show();
                System.out.println("Redirecionado para consultarProdutos.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena consultarProdutos.fxml.");
            }
        });
    }

    private void showConfirmationAlert(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Saída");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza de que deseja sair?");

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
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
