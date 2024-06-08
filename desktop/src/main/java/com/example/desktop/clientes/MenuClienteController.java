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

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @FXML
    public void initialize() {
        System.out.println("Inicializando MenuClienteController...");

        consultarProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/consultarProdutos.fxml"));
        pagamentosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/pagarPedidos.fxml"));
        pedidosButton.setOnAction(this::efetuarPedidos);
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
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
            showAlert("Erro", "Não foi possível abrir a cena: efetuarPedido.fxml");
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
            loadScene(event, "/com/example/desktop/firstPage.fxml");
        }
    }

    private void showAlert(String title, String message) {
        System.out.println("Mostrando alerta: " + title + " - " + message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
