package com.example.desktop.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PagarEncomendasController {

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private TableView<?> customersTable;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button goBack;

    @FXML
    private TableColumn<?, ?> idEncomendaField;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TableColumn<?, ?> quantidadeField;

    @FXML
    private Button pagarEncomendaButton;

    @FXML
    private Button sairButton;

    @FXML
    private TableColumn<?, ?> tipoLeiteField;

    @FXML
    private Button voltarButton;

    @FXML
    public void initialize() {
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));

        pagarEncomendaButton.setOnAction(event -> abrirPopUpPagamento());
    }

    private void abrirPopUpPagamento() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktop/Admin/PagamentoPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Escolha o Método de Pagamento");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao abrir o pop-up de pagamento.");
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
