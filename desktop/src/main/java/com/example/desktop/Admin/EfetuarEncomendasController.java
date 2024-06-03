package com.example.desktop.Admin;

import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

public class EfetuarEncomendasController {

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button efetuarEncomendaButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TextField quantidadeField;

    @FXML
    private Button sairButton;

    @FXML
    private ChoiceBox<String> tipoLeiteField;

    @FXML
    private Button voltarButton;

    private EncomendaBll encomendaBll;

    public EfetuarEncomendasController() {
        this.encomendaBll = new EncomendaBll();
    }

    @FXML
    private void initialize() {
        tipoLeiteField.getItems().addAll("Leite de Vaca", "Leite de Cabra", "Leite de Ovelha");
        efetuarEncomendaButton.setOnAction(event -> efetuarEncomenda());
    }

    private void efetuarEncomenda() {
        String quantidade = quantidadeField.getText();
        String tipoLeite = tipoLeiteField.getValue();

        if (quantidade.isEmpty() || tipoLeite == null) {
            mostrarAlerta("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        try {
            float quantidadeLitros = Float.parseFloat(quantidade);
            Encomenda encomenda = new Encomenda();
            encomenda.setQuantidade(quantidadeLitros);
            encomenda.setTipoLeite(tipoLeite);
            encomenda.setData(Date.valueOf(LocalDate.now()));
            encomenda.setIdfornecedor(1); // Exemplo de idfornecedor

            encomendaBll.salvarEncomenda(encomenda);
            mostrarAlerta("Sucesso", "Encomenda efetuada com sucesso!");
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Por favor, insira uma quantidade válida.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
