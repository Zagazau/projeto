package com.example.desktop.clientes;

import BLL.PedidoBll;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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

    private int idCliente; // Adicionado para armazenar o id do cliente

    public void setIdCliente(int idCliente) { // Método para definir o id do cliente
        this.idCliente = idCliente;
    }

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

        efetuarPedidoButton.setOnAction(event -> {
            System.out.println("Botão Efetuar Pedido pressionado!");
            efetuarPedido();
        });
    }

    private MenuItem createMenuItem(String text) {
        MenuItem menuItem = new MenuItem(text);
        menuItem.setOnAction(event -> queijoField.setText(text));
        return menuItem;
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

    private void efetuarPedido() {
        try {
            int quantidade = Integer.parseInt(quantidadeField.getText());
            java.sql.Date data = Date.valueOf(LocalDate.now());

            PedidoBll pedidoBll = new PedidoBll();
            pedidoBll.adicionarPedido(idCliente, quantidade, data);

            showAlert(Alert.AlertType.INFORMATION, "Pedido Efetuado", "Seu pedido foi efetuado com sucesso!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro no Pedido", "Quantidade inválida.");
        }
    }
}
