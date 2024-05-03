package com.example.desktop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdminController {
    @FXML
    private Button consultarFaturacaoButton;

    @FXML
    private Button consultarProducaoButton;

    @FXML
    private Button controlarStockButton;

    @FXML
    private Button encomendarLeiteButton;

    @FXML
    private Button goBack;

    @FXML
    private Button pagarEncomendasButton;

    @FXML
    private Button sairButton;

    @FXML
    public void initialize() {
        consultarFaturacaoButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/ConsultaFaturacao.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) consultarFaturacaoButton.getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        consultarProducaoButton.setOnAction(event -> {
            // Lógica para abrir a página de consulta de produção
        });

        controlarStockButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/controlarStockAdmin.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) controlarStockButton.getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        encomendarLeiteButton.setOnAction(event -> {
            // Lógica para abrir a página de encomenda de leite
        });

        pagarEncomendasButton.setOnAction(event -> {
            // Lógica para abrir a página de pagamento de encomendas
        });

        sairButton.setOnAction(event -> {
            // Lógica para voltar para a página anterior
            Stage stage = (Stage) sairButton.getScene().getWindow();
            stage.close();
        });
    }
}
