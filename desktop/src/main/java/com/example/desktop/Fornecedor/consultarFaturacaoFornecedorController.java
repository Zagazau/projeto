package com.example.desktop.Fornecedor;

import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class consultarFaturacaoFornecedorController {

    @FXML
    private Button consultarEncomendasButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button inserirProdutosButton;

    @FXML
    private Button sairButton;

    @FXML
    private TableView<Encomenda> customersTable;

    @FXML
    private TableColumn<Encomenda, Integer> idEncomendaField;

    @FXML
    private TableColumn<Encomenda, String> tipoLeiteField;

    @FXML
    private TableColumn<Encomenda, Float> quantidadeField;

    @FXML
    private TableColumn<Encomenda, Float> precoField;

    @FXML
    private Button voltarButton;

    @FXML
    private Label totalLabel;

    private EncomendaBll encomendaBll;

    @FXML
    public void initialize() {
        encomendaBll = new EncomendaBll();

        idEncomendaField.setCellValueFactory(new PropertyValueFactory<>("idencomenda"));
        tipoLeiteField.setCellValueFactory(new PropertyValueFactory<>("tipoLeite"));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        precoField.setCellValueFactory(new PropertyValueFactory<>("valor"));

        loadEncomendas();

        sairButton.setOnAction(event -> showConfirmationAlert(event, "/com/example/desktop/firstPage.fxml"));
        consultarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/consultarEncomendasFornecedor.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/consultarFaturacaoFornecedor.fxml"));
        inserirProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/inserirProdutos.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/fornecedorMenu.fxml"));
    }

    private void loadEncomendas() {
        List<Encomenda> encomendas = encomendaBll.listarEncomendas();
        ObservableList<Encomenda> encomendasObservableList = FXCollections.observableArrayList(encomendas);
        customersTable.setItems(encomendasObservableList);

        float total = 0;
        for (Encomenda encomenda : encomendas) {
            total += encomenda.getValor();
        }
        totalLabel.setText(String.format("TOTAL: %.2f", total));
    }

    private void loadScene(javafx.event.ActionEvent event, String fxmlFile) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource(fxmlFile));
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            showAlert(javafx.scene.control.Alert.AlertType.ERROR, "Erro", "Erro ao carregar a página: " + fxmlFile);
        }
    }

    private void showAlert(javafx.scene.control.Alert.AlertType alertType, String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmationAlert(javafx.event.ActionEvent event, String fxmlFile) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja sair?");

        java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
            loadScene(event, fxmlFile);
        }
    }
}
