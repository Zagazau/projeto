package com.example.desktop.Admin;

import entity.Encomenda;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PagamentoPopUpController {

    @FXML
    private ChoiceBox<String> metodoPagamentoChoiceBox;

    private PagarEncomendasController parentController;


    @FXML
    public void initialize() {
        metodoPagamentoChoiceBox.getItems().addAll();
        metodoPagamentoChoiceBox.getSelectionModel().selectFirst();
    }

    public void setParentController(PagarEncomendasController parentController) {
        this.parentController = parentController;
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
