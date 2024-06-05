package com.example.desktop.clientes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MenuClienteController {

    @FXML
    private Button consultarProdutosButton;

    @FXML
    private Button pagamentosButton;

    @FXML
    private Button pedidosButton;

    @FXML
    private Button sairButton;

    private int idCliente; // Adicionado para armazenar o id do cliente

    public void setIdCliente(int idCliente) { // Método para definir o id do cliente
        this.idCliente = idCliente;
    }

    @FXML
    public void initialize() {
        System.out.println("Inicializando MenuClienteController...");

        if (consultarProdutosButton != null) {
            consultarProdutosButton.setOnAction(this::consultarProdutos);
        }
        if (pagamentosButton != null) {
            pagamentosButton.setOnAction(this::pagarPedidos);
        }
        if (pedidosButton != null) {
            pedidosButton.setOnAction(this::efetuarPedidos);
        }
        if (sairButton != null) {
            sairButton.setOnAction(this::sair);
        }
    }

    @FXML
    private void consultarProdutos(ActionEvent event) {
        System.out.println("Botão Consultar Produtos pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/consultarProdutos.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Consultar Produtos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: consultarProdutos.fxml");
        }
    }

    @FXML
    private void pagarPedidos(ActionEvent event) {
        System.out.println("Botão Pagar Pedidos pressionado!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/pagarPedidos.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Pagar Pedidos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: pagarPedidos.fxml");
        }
    }

    @FXML
    private void efetuarPedidos(ActionEvent event) {
        System.out.println("Botão Efetuar Pedidos pressionado!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/Cliente/efetuarPedido.fxml"));
            Parent root = loader.load();
            efetuarPedidosClienteController controller = loader.getController();
            controller.setIdCliente(idCliente);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Efetuar Pedidos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena: efetuarPedido.fxml");
        }
    }

    @FXML
    private void sair(ActionEvent event) {
        System.out.println("Botão Sair pressionado!");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja sair?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Página Inicial");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página inicial.");
            }
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
