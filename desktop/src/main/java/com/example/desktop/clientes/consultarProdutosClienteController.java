package com.example.desktop.clientes;

import BLL.ProdutoBll;
import entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class consultarProdutosClienteController {

    @FXML
    private Button voltarButton;

    @FXML
    private Button consultarProdutosButton;

    @FXML
    private TreeTableColumn<Produto, Integer> idpedidoField;

    @FXML
    private Button pagamentosButton;

    @FXML
    private Button pedidosButton;

    @FXML
    private TreeTableColumn<Produto, Integer> quantidadeField;

    @FXML
    private TreeTableColumn<Produto, String> queijoField;

    @FXML
    private Button sairButton;

    @FXML
    private TreeTableColumn<Produto, Float> valorField;

    @FXML
    private TreeTableView<Produto> productsTable;

    private ProdutoBll produtoBll;

    @FXML
    public void initialize() {

        consultarProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/consultarProdutos.fxml"));
        pagamentosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/pagarPedidos.fxml"));
        pedidosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/efetuarPedido.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Cliente/clienteMenu.fxml"));

        produtoBll = new ProdutoBll();
        configurarColunasTabela();
        carregarProdutos();
    }

    private void configurarColunasTabela() {
        idpedidoField.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        quantidadeField.setCellValueFactory(new TreeItemPropertyValueFactory<>("quantidade"));
        queijoField.setCellValueFactory(new TreeItemPropertyValueFactory<>("nome"));
        valorField.setCellValueFactory(new TreeItemPropertyValueFactory<>("valor"));
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoBll.obterProdutosAdicionadosPor("admin");
        ObservableList<TreeItem<Produto>> produtoList = FXCollections.observableArrayList();

        for (Produto produto : produtos) {
            TreeItem<Produto> item = new TreeItem<>(produto);
            produtoList.add(item);
        }

        TreeItem<Produto> root = new TreeItem<>(new Produto());
        root.getChildren().addAll(produtoList);
        productsTable.setRoot(root);
        productsTable.setShowRoot(false);
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
