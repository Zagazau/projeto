package com.example.desktop.Fornecedor;

import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class consultarEncomendasFornecedorController {

    @FXML
    private Button consultarEncomendasButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button inserirProdutosButton;

    @FXML
    private Button sairButton;

    @FXML
    private TableView<Encomenda> encomendasTable;

    @FXML
    private TableColumn<Encomenda, Integer> idEncomendaField;

    @FXML
    private TableColumn<Encomenda, String> tipoLeiteField;

    @FXML
    private TableColumn<Encomenda, Float> quantidadeField;

    private EncomendaBll encomendaBll;

    @FXML
    public void initialize() {
        encomendaBll = new EncomendaBll();

        idEncomendaField.setCellValueFactory(new PropertyValueFactory<>("idencomenda"));
        tipoLeiteField.setCellValueFactory(new PropertyValueFactory<>("tipoLeite"));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        loadEncomendas();

        sairButton.setOnAction(event -> showConfirmationAlert(event, "/com/example/desktop/firstPage.fxml"));
        consultarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/consultarEncomendasFornecedor.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/consultarFaturacaoFornecedor.fxml"));
        inserirProdutosButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Fornecedor/inserirProdutos.fxml"));
    }

    private void loadEncomendas() {
        List<Encomenda> encomendas = encomendaBll.listarEncomendas();
        ObservableList<Encomenda> encomendasObservableList = FXCollections.observableArrayList(encomendas);
        encomendasTable.setItems(encomendasObservableList);
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
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a página: " + fxmlFile);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirmationAlert(javafx.event.ActionEvent event, String fxmlFile) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            loadScene(event, fxmlFile);
        }
    }
}
