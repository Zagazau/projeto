package com.example.desktop.Admin;

import BLL.ProdutoBll;
import entity.Admin;
import entity.Produto;
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
import javafx.stage.Stage;

import java.io.IOException;

public class RemoverProdutoController {

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private TableColumn<Produto, Integer> idProdutoField;

    @FXML
    private TableColumn<Produto, String> nomeProdutoField;

    @FXML
    private TableColumn<Produto, Integer> quanrtidadeField;

    @FXML
    private Button removerProdutoButton;

    @FXML
    private TableView<Produto> removerTable;

    @FXML
    private Button sairButton;

    @FXML
    private Button voltarButton;

    private ObservableList<Produto> produtos;
    private ProdutoBll produtoBll;

    @FXML
    public void initialize() {
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturacaoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));

        produtoBll = new ProdutoBll();
        produtos = FXCollections.observableArrayList(produtoBll.obterProdutosAdicionadosPor("admin"));

        idProdutoField.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeProdutoField.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quanrtidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        removerTable.setItems(produtos);

        removerProdutoButton.setOnAction(event -> removerProduto());
    }

    private void removerProduto() {
        Produto produtoSelecionado = removerTable.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            produtoBll.removerProduto(produtoSelecionado.getId());
            produtos.remove(produtoSelecionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum produto selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um produto para remover.");
            alert.showAndWait();
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
            showAlert("Erro", "Erro ao carregar a página: " + fxmlFile);
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
