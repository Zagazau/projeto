package com.example.desktop.clientes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PagamentoClientePopUpController {

    @FXML
    private ChoiceBox<String> metodoPagamentoChoiceBox;

    private PagarPedidoController parentController;

    public void setParentController(PagarPedidoController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        metodoPagamentoChoiceBox.getItems().addAll();
        metodoPagamentoChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void efetuarPagamento() {
        String metodoSelecionado = metodoPagamentoChoiceBox.getSelectionModel().getSelectedItem();

        if (parentController != null) {
            parentController.processarPagamento(metodoSelecionado);
        }

        Stage stage = (Stage) metodoPagamentoChoiceBox.getScene().getWindow();
        stage.close();
    }
}
