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

        // Configurar as colunas da tabela
        idEncomendaField.setCellValueFactory(new PropertyValueFactory<>("idencomenda"));
        tipoLeiteField.setCellValueFactory(new PropertyValueFactory<>("tipoLeite"));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        // Carregar os dados das encomendas
        loadEncomendas();

        sairButton.setOnAction(event -> {
            System.out.println("Botão Sair pressionado!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Saída");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja sair?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Página Anterior");
                    stage.show();
                    System.out.println("Redirecionado para firstPage.fxml com sucesso");
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar para a página anterior.");
                }
            }
        });

        consultarEncomendasButton.setOnAction(event -> {
            System.out.println("Botão Consultar Encomendas pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarEncomendasFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Encomendas");
                stage.show();
                System.out.println("Redirecionado para consultarEncomendas.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena consultarEncomendas.fxml.");
            }
        });

        consultarFaturacaoButton.setOnAction(event -> {
            System.out.println("Botão Consultar Faturação pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/consultarFaturacaoFornecedor.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Consultar Faturação");
                stage.show();
                System.out.println("Redirecionado para consultarFaturacao.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena consultarFaturacao.fxml.");
            }
        });

        inserirProdutosButton.setOnAction(event -> {
            System.out.println("Botão Inserir Produto pressionado!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/inserirProdutos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Inserir Produto");
                stage.show();
                System.out.println("Redirecionado para inserirProduto.fxml com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a cena inserirProduto.fxml.");
            }
        });
    }

    private void loadEncomendas() {
        List<Encomenda> encomendas = encomendaBll.listarEncomendas();
        ObservableList<Encomenda> encomendasObservableList = FXCollections.observableArrayList(encomendas);
        encomendasTable.setItems(encomendasObservableList);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
