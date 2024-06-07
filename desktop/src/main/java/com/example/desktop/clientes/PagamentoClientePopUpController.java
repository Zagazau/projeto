package com.example.desktop.clientes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PagamentoClientePopUpController {

    @FXML
    private ChoiceBox<String> metodoPagamentoChoiceBox;

    @FXML
    public void initialize() {
        metodoPagamentoChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void efetuarPagamento() {
        String metodoSelecionado = metodoPagamentoChoiceBox.getSelectionModel().getSelectedItem();
        // Lógica para processar o pagamento com o método selecionado

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pagamento Efetuado");
        alert.setHeaderText(null);
        alert.setContentText("Pagamento efetuado com sucesso usando " + metodoSelecionado);
        alert.showAndWait();

        // Fechar o pop-up após o pagamento
        Stage stage = (Stage) metodoPagamentoChoiceBox.getScene().getWindow();
        stage.close();
    }
}
