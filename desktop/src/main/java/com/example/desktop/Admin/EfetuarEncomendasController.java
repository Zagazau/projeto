package com.example.desktop.Admin;

import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        consultarProducaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarProducao.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
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

    private void efetuarEncomenda() {
        String quantidade = quantidadeField.getText();
        String tipoLeite = tipoLeiteField.getValue();

        if (quantidade.isEmpty() || tipoLeite == null) {
            showAlert("Erro", "Por favor, preencha todos os campos.");
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
            showAlert("Sucesso", "Encomenda efetuada com sucesso!");
        } catch (NumberFormatException e) {
            showAlert("Erro", "Por favor, insira uma quantidade válida.");
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
