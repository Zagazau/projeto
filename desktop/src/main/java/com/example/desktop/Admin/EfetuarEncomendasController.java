package com.example.desktop.Admin;

import BLL.EncomendaBll;
import BLL.ProdutoBll;
import entity.Encomenda;
import entity.Produto;
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
import java.util.List;

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
    private ProdutoBll produtoBll;

    public EfetuarEncomendasController() {
        this.encomendaBll = new EncomendaBll();
        this.produtoBll = new ProdutoBll();
    }

    @FXML
    private void initialize() {
        loadProdutosFornecedores();
        efetuarEncomendaButton.setOnAction(event -> efetuarEncomenda());
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturaçãoAdmin.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
    }

    private void loadProdutosFornecedores() {
        List<Produto> produtos = produtoBll.obterProdutosAdicionadosPor("fornecedor");
        for (Produto produto : produtos) {
            if (produto.getQuantidade() > 0) {
                tipoLeiteField.getItems().add(produto.getNome());
            }
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
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a página: " + fxmlFile);
        }
    }

    private void efetuarEncomenda() {
        String quantidade = quantidadeField.getText();
        String tipoLeite = tipoLeiteField.getValue();

        if (quantidade.isEmpty() || tipoLeite == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, preencha todos os campos.");
            return;
        }

        try {
            float quantidadeLitros = Float.parseFloat(quantidade);
            Produto produtoSelecionado = produtoBll.obterProdutoPorNome(tipoLeite);

            if (produtoSelecionado.getQuantidade() < quantidadeLitros) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Quantidade indisponível no estoque.");
                return;
            }

            Encomenda encomenda = new Encomenda();
            encomenda.setQuantidade(quantidadeLitros);
            encomenda.setTipoLeite(tipoLeite);
            encomenda.setData(Date.valueOf(LocalDate.now()));
            encomenda.setIdfornecedor(1);
            encomenda.setIdproduto(produtoSelecionado.getId());
            encomenda.setValor(produtoSelecionado.getValor() * quantidadeLitros);

            encomendaBll.salvarEncomenda(encomenda);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Encomenda efetuada com sucesso!");

            produtoSelecionado.setQuantidade((int) (produtoSelecionado.getQuantidade() - quantidadeLitros));
            produtoBll.atualizarProduto(produtoSelecionado);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira uma quantidade válida.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
