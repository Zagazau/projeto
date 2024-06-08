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
    private ChoiceBox<String> queijoField;

    @FXML
    private Button sairButton;

    private int idCliente; // Adicionado para armazenar o id do cliente

    private ProdutoBll produtoBll;
    private PedidoBll pedidoBll;

    public void setIdCliente(int idCliente) { // Método para definir o id do cliente
        this.idCliente = idCliente;
    }

    @FXML
    public void initialize() {
        produtoBll = new ProdutoBll();
        pedidoBll = new PedidoBll();

        carregarProdutosAdmin();

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

        efetuarPedidoButton.setOnAction(event -> {
            System.out.println("Botão Efetuar Pedido pressionado!");
            efetuarPedido();
        });
    }

    private void carregarProdutosAdmin() {
        List<Produto> produtosAdmin = produtoBll.obterProdutosAdicionadosPor("admin");
        for (Produto produto : produtosAdmin) {
            queijoField.getItems().add(produto.getNome());
        }
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
            pedido.setIdproduto(produto.getId());

            // Corrigindo o valor do pedido para ser do tipo Double
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
