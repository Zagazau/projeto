package com.example.desktop.Admin;

import BLL.FaturaCompraBll;
import BLL.EncomendaBll;
import entity.Encomenda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import entity.PagamentoEnum;

import java.io.IOException;
import java.util.List;

public class PagarEncomendasController {

    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private TableView<Encomenda> customersTable;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private TableColumn<Encomenda, Integer> idEncomendaField;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private TableColumn<Encomenda, Float> quantidadeField;

    @FXML
    private Button pagarEncomendaButton;

    @FXML
    private Button sairButton;

    @FXML
    private TableColumn<Encomenda, String> tipoLeiteField;

    @FXML
    private TableColumn<Encomenda, Float> valorField;

    @FXML
    private Button voltarButton;

    private EncomendaBll encomendaBll;
    private FaturaCompraBll faturaCompraBll;
    private ObservableList<Encomenda> encomendaObservableList;

    @FXML
    public void initialize() {
        encomendaBll = new EncomendaBll();
        faturaCompraBll = new FaturaCompraBll();
        configurarColunasTabela();
        carregarEncomendas();

        controlarStockButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/controlarStockAdmin.fxml"));
        pagarEncomendasButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/pagarEncomendasAdmin.fxml"));
        consultarFaturacaoButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/consultarFaturacaoAdmin.fxml"));
        encomendarLeiteButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/encomendasAdmin.fxml"));
        sairButton.setOnAction(event -> loadScene(event, "/com/example/desktop/firstPage.fxml"));
        voltarButton.setOnAction(event -> loadScene(event, "/com/example/desktop/Admin/adminMenu.fxml"));

        pagarEncomendaButton.setOnAction(event -> abrirPopUpPagamento());
    }

    private void configurarColunasTabela() {
        idEncomendaField.setCellValueFactory(new PropertyValueFactory<>("idencomenda"));
        tipoLeiteField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoLeite()));
        quantidadeField.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        valorField.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void carregarEncomendas() {
        List<Encomenda> encomendas = encomendaBll.listarEncomendas();
        encomendaObservableList = FXCollections.observableArrayList(encomendas);
        customersTable.setItems(encomendaObservableList);
    }

    private void abrirPopUpPagamento() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/desktop/Admin/PagamentoPopUp.fxml"));
            Parent parent = fxmlLoader.load();

            PagamentoPopUpController controller = fxmlLoader.getController();

            controller.setParentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Escolha o Método de Pagamento");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao abrir o pop-up de pagamento.");
        }
    }

    public void processarPagamento(String metodoPagamento) {
        Encomenda encomendaSelecionada = customersTable.getSelectionModel().getSelectedItem();
        if (encomendaSelecionada != null) {
            int idTipoPagamento = obterIdTipoPagamento(metodoPagamento);
            float valor = encomendaSelecionada.getValor().floatValue();
            float quantidade = (float) encomendaSelecionada.getQuantidade();

            try {
                // Busca a encomenda completa pelo id
                Encomenda encomendaCompleta = encomendaBll.obterEncomendaPorId(encomendaSelecionada.getIdencomenda());

                // Process payment and add Faturacompra
                faturaCompraBll.adicionarFaturaCompra(
                        0,
                        encomendaCompleta, // Passa a encomenda completa aqui
                        idTipoPagamento,
                        valor,
                        (int) quantidade
                );

                // Remove from UI list
                encomendaObservableList.remove(encomendaSelecionada);

                showAlert(Alert.AlertType.INFORMATION, "Pagamento Efetuado", "Pagamento efetuado com sucesso usando " + metodoPagamento);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao processar o pagamento: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Nenhuma encomenda selecionada.");
        }
    }




    private int obterIdTipoPagamento(String metodoPagamento) {
        return PagamentoEnum.fromNome(metodoPagamento).getId();
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
