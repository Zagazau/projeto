package com.example.desktop.Admin;

import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CancelarEncomendaController {

    @FXML
    private Button cancelarEncomendaButton;

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TableView<Encomenda> customersTable;

    @FXML
    private TableColumn<Encomenda, Integer> idEncomenda;

    @FXML
    private TableColumn<Encomenda, Integer> id_Fornecedor;

    @FXML
    private TableColumn<Encomenda, String> dataEncomenda;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button goBack;

    @FXML
    private Button sairButton;

    @FXML
    private Button voltarButton;

    private EncomendaBll encomendaBll;

    @FXML
    public void initialize() {
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));

        encomendaBll = new EncomendaBll();

        idEncomenda.setCellValueFactory(new PropertyValueFactory<>("idencomenda"));
        id_Fornecedor.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
        dataEncomenda.setCellValueFactory(new PropertyValueFactory<>("data"));

        loadOrders();
    }

    private void loadOrders() {
        List<Encomenda> orders = encomendaBll.listarEncomendas();
        if (orders != null) {
            System.out.println("Encomendas carregadas: " + orders.size());
            for (Encomenda order : orders) {
                System.out.println(order.getIdencomenda() + " - " + order.getIdfornecedor() + " - " + order.getData());
            }
            customersTable.getItems().setAll(orders);
        } else {
            System.out.println("Nenhuma encomenda encontrada.");
        }
    }

    @FXML
    private void onCancelOrder() {
        Encomenda selectedOrder = customersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            encomendaBll.excluirEncomenda(selectedOrder.getIdencomenda());
            customersTable.getItems().remove(selectedOrder);
            System.out.println("Encomenda cancelada: " + selectedOrder.getIdencomenda());
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
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
