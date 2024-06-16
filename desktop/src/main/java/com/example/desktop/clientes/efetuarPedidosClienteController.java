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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class efetuarPedidosClienteController {

    @FXML
    private Button voltarButton;

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

    private int idCliente;

    private ProdutoBll produtoBll;
    private PedidoBll pedidoBll;

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @FXML
    public void initialize() {
        produtoBll = new ProdutoBll();
        pedidoBll = new PedidoBll();

        consultarProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/consultarProdutos.fxml"));
        pagamentosButton.setOnAction(event -> loadPagamentosScene(event));
        pedidosButton.setOnAction(event -> loadEfetuarPedidoScene(event));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/clienteMenu.fxml"));

        carregarProdutosAdmin();

        efetuarPedidoButton.setOnAction(event -> {
            System.out.println("Botão Efetuar Pedido pressionado!");
            efetuarPedido();
        });
    }

    private void carregarProdutosAdmin() {
        List<Produto> produtosAdmin = produtoBll.obterProdutosAdicionadosPor("admin");
        for (Produto produto : produtosAdmin) {
            if (produto.getQuantidade() > 0) {
                queijoField.getItems().add(produto.getNome());
            }
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
            showAlert(Alert.AlertType.ERROR,"Erro", "Erro ao carregar a página: " + fxmlFile);
        }
    }

    private void loadPagamentosScene(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/Cliente/pagarPedidos.fxml"));
            Parent root = loader.load();
            PagarPedidoController controller = loader.getController();
            controller.setIdCliente(idCliente);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Erro", "Erro ao carregar a página de pagamentos.");
        }
    }

    private void loadEfetuarPedidoScene(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/Cliente/efetuarPedido.fxml"));
            Parent root = loader.load();
            efetuarPedidosClienteController controller = loader.getController();
            controller.setIdCliente(idCliente);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR,"Erro", "Erro ao carregar a página de efetuar pedido.");
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
        if (idCliente == 0) {
            showAlert(Alert.AlertType.ERROR, "Erro", "ID do cliente inválido. Por favor, faça login novamente.");
            return;
        }

        try {
            String produtoNome = queijoField.getValue();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            Produto produto = produtoBll.obterProdutoPorNome(produtoNome);

            if (quantidade > produto.getQuantidade()) {
                showAlert(Alert.AlertType.ERROR, "Erro no Pedido", "Quantidade solicitada é maior do que a disponível.");
                return;
            }

            Pedido pedido = new Pedido();
            pedido.setIdcliente(idCliente);
            pedido.setQuantidade(quantidade);
            pedido.setData(Date.valueOf(LocalDate.now()));
            pedido.setProduto(produto);
            double valorPedido = produto.getValor() * quantidade;
            pedido.setValor(valorPedido);

            pedidoBll.adicionarPedido(pedido);

            produto.setQuantidade(produto.getQuantidade() - quantidade);
            produtoBll.atualizarProduto(produto);

            showAlert(Alert.AlertType.INFORMATION, "Pedido Efetuado", "Seu pedido foi efetuado com sucesso!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro no Pedido", "Quantidade inválida.");
        }
    }
}
