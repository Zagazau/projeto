package com.example.desktop.clientes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PagarPedidoController {

    @FXML
    private Button consultarProdutosButton;

    @FXML
    private TreeTableColumn<?, ?> dataField;

    @FXML
    private TreeTableColumn<?, ?> idpedidoField;

    @FXML
    private Button pagamentosButton;

    @FXML
    private Button pagarButton;

    @FXML
    private Button pedidosButton;

    @FXML
    private TreeTableColumn<?, ?> quantidadeField;

    @FXML
    private TreeTableColumn<?, ?> queijoField;

    @FXML
    private Button sairButton;

    @FXML
    private TreeTableColumn<?, ?> valorField;

    @FXML
    private Button voltarButton;

    @FXML
    public void initialize() {
        pagarButton.setOnAction(event -> abrirPopUpPagamento());
    }

    private void abrirPopUpPagamento() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktop/clientes/PagamentoClientePopUp.fxml"));
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
