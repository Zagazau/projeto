package com.example.desktop.clientes;

import BLL.FaturaVendaBll;
import BLL.PedidoBll;
import entity.Pedido;
import entity.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import entity.PagamentoEnum;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PagarPedidoController {

    @FXML
    private Button consultarProdutosButton;

    @FXML
    private TableView<Pedido> tableView;

    @FXML
    private TableColumn<Pedido, Date> dataField;

    @FXML
    private TableColumn<Pedido, Integer> idpedidoField;

    @FXML
    private Button pagamentosButton;

    @FXML
    private Button pagarButton;

    @FXML
    private Button pedidosButton;

    @FXML
    private TableColumn<Pedido, Integer> quantidadeField;

    @FXML
    private TableColumn<Pedido, String> queijoField;

    @FXML
    private Button sairButton;

    @FXML
    private TableColumn<Pedido, Double> valorField;

    @FXML
    private Button voltarButton;

    private PedidoBll pedidoBll;
    private FaturaVendaBll faturaVendaBll;
    private ObservableList<Pedido> pedidosObservableList;

    @FXML
    public void initialize() {
        consultarProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/consultarProdutos.fxml"));
        pagamentosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/pagarPedidos.fxml"));
        pedidosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/efetuarPedido.fxml"));

        pedidoBll = new PedidoBll();
        faturaVendaBll = new FaturaVendaBll();

        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/clienteMenu.fxml"));

        idpedidoField.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
        dataField.setCellValueFactory(new PropertyValueFactory<>("data"));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        valorField.setCellValueFactory(new PropertyValueFactory<>("valor"));
        queijoField.setCellValueFactory(cellData -> {
            Produto produto = cellData.getValue().getProduto();
            return new SimpleStringProperty(produto != null ? produto.getNome() : "N/A");
        });

        carregarPedidos();

        pagarButton.setOnAction(event -> abrirPopUpPagamento());
    }

    private void carregarPedidos() {
        List<Pedido> pedidos = pedidoBll.obterTodosPedidos();
        pedidosObservableList = FXCollections.observableArrayList(pedidos);
        tableView.setItems(pedidosObservableList);
    }

    private void abrirPopUpPagamento() {
        try {
            String fxmlPath = "/com/example/desktop/Cliente/PagamentoClientePopUp.fxml";
            System.out.println("Loading FXML from: " + getClass().getResource(fxmlPath));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent parent = fxmlLoader.load();

            PagamentoClientePopUpController controller = fxmlLoader.getController();
            controller.setParentController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Escolha o Método de Pagamento");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o pop-up de pagamento.");
        }
    }


    public void processarPagamento(String metodoPagamento) {
        Pedido pedidoSelecionado = tableView.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            int idTipoPagamento = obterIdTipoPagamento(metodoPagamento);

            float valorFloat = pedidoSelecionado.getValor().floatValue();

            faturaVendaBll.adicionarFaturaVenda(
                    0,
                    pedidoSelecionado.getIdpedido(),
                    idTipoPagamento,
                    pedidoSelecionado.getIdcliente(),
                    valorFloat,
                    pedidoSelecionado.getQuantidade()
            );

            pedidosObservableList.remove(pedidoSelecionado);

            showAlert(Alert.AlertType.INFORMATION, "Pagamento Efetuado", "Pagamento efetuado com sucesso usando " + metodoPagamento);
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhum pedido selecionado.");
        }
    }

    private int obterIdTipoPagamento(String metodoPagamento) {
        return PagamentoEnum.fromNome(metodoPagamento).getId();
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
