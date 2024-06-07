package com.example.desktop.clientes;

import BLL.PedidoBll;
import BLL.ProdutoBll;
import entity.Pedido;
import entity.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
    private ChoiceBox<String> queijoField;

    @FXML
    private Button sairButton;

    private int idCliente; // Adicionado para armazenar o id do cliente

    private PedidoBll pedidoBll;
    private ProdutoBll produtoBll;

    public void setIdCliente(int idCliente) { // Método para definir o id do cliente
        this.idCliente = idCliente;
    }

    @FXML
    public void initialize() {
        pedidoBll = new PedidoBll();
        produtoBll = new ProdutoBll();

        queijoField.getItems().addAll("Queijo Tipo 1", "Queijo Tipo 2", "Queijo Tipo 3");
        efetuarPedidoButton.setOnAction(event -> efetuarPedido());
        consultarProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/consultarProdutos.fxml"));
        pagamentosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/pagarPedidos.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        backButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/clienteMenu.fxml"));
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

    private void efetuarPedido() {
        try {
            int quantidade = Integer.parseInt(quantidadeField.getText());
            String tipoQueijo = queijoField.getValue();
            java.sql.Date data = Date.valueOf(LocalDate.now());

            Produto produto = produtoBll.obterProdutoPorNome(tipoQueijo);
            if (produto == null) {
                showAlert("Erro", "Produto não encontrado.");
                return;
            }

            Pedido pedido = new Pedido();
            pedido.setIdcliente(idCliente);
            pedido.setQuantidade(quantidade);
            pedido.setData(data);
            pedido.setIdproduto(produto.getId());
            pedido.setValor(produto.getValor() * quantidade);

            pedidoBll.adicionarPedido(pedido);

            showAlert("Pedido Efetuado", "Seu pedido foi efetuado com sucesso!");
        } catch (NumberFormatException e) {
            showAlert( "Erro no Pedido", "Quantidade inválida.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert( "Erro no Pedido", "Erro ao efetuar pedido.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
