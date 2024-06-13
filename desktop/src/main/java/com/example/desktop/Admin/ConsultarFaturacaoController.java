package com.example.desktop.Admin;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ConsultarFaturacaoController {

    @FXML
    private Button backButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private TableView<Pedido> customersTable;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button goBack;

    @FXML
    private TableColumn<Pedido, Integer> idEncomendaField;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TableColumn<Pedido, Float> preçoField;

    @FXML
    private TableColumn<Pedido, Integer> quantidadeField;

    @FXML
    private Button sairButton;

    @FXML
    private TableColumn<Pedido, String> tipoQueijoField;

    @FXML
    private Label totalLabel;

    private PedidoBll pedidoBll;

    @FXML
    public void initialize() {
        pedidoBll = new PedidoBll();

        idEncomendaField.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        preçoField.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tipoQueijoField.setCellValueFactory(cellData -> {
            Produto produto = cellData.getValue().getProduto();
            return new SimpleStringProperty(produto != null ? produto.getNome() : "N/A");
        });

        loadOrders();

        backButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/menuAdmin.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
    }

    private void loadOrders() {
        List<Pedido> pedidos = pedidoBll.obterTodosPedidos();
        ObservableList<Pedido> pedidosObservableList = FXCollections.observableArrayList(pedidos);
        customersTable.setItems(pedidosObservableList);
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (Pedido pedido : customersTable.getItems()) {
            if (pedido.getValor() != null) {
                total += pedido.getValor();
            }
        }
        totalLabel.setText("TOTAL: " + String.format("%.2f", total));
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
            showAlert("Erro", "Erro ao carregar a página: " + fxmlFile + "\n" + e.getMessage());
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
