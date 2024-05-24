package com.example.desktop.clientes;

import BLL.ClienteBLL;
import entity.Cliente;
import entity.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RegistarClienteController {

    @FXML
    private TextField codPostalField;

    @FXML
    private TextField nifField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField numPortaField;

    @FXML
    private TextField passField;

    @FXML
    private Button registarClienteButton;

    @FXML
    private TextField ruaField;

    @FXML
    private Button sairButton;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField usernameField;

    @FXML
    private void initialize() {
        System.out.println("Initializing RegistarClienteController");
        registarClienteButton.setOnAction(this::registerCliente);
        sairButton.setOnAction(this::redirectToLogin);
    }

    @FXML
    private void registerCliente(ActionEvent event) {
        System.out.println("registerCliente method called");
        if (validateFields()) {
            System.out.println("Fields validation passed");
            Cliente cliente = new Cliente();
            cliente.setNome(nomeField.getText().trim());
            cliente.setNif(Integer.parseInt(nifField.getText().trim()));
            cliente.setRua(ruaField.getText().trim());
            cliente.setNumporta(Integer.parseInt(numPortaField.getText().trim()));
            cliente.setCodpostal(codPostalField.getText().trim());
            cliente.setTelefone(Integer.parseInt(telefoneField.getText().trim()));
            cliente.setUsername(usernameField.getText().trim());
            cliente.setSenha(encryptPassword(passField.getText().trim()));

            try {
                System.out.println("Persisting client data: " + cliente.toString());
                ClienteBLL.criar(cliente);
                System.out.println("Cliente created successfully");
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cliente registrado com sucesso!");
                redirectToLogin(event);
            } catch (Exception e) {
                System.err.println("Error creating Cliente");
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao registrar o cliente.");
            }
        } else {
            System.out.println("Fields validation failed");
        }
    }

    private String encryptPassword(String password) {
        String salt = PasswordUtils.generateSalt();
        try {
            return PasswordUtils.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password");
            return null;
        }
    }

    private void redirectToLogin(ActionEvent event) {
        System.out.println("redirectToLogin method called");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/firstPage.fxml"));
            Scene loginScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();
            System.out.println("Redirected to login.fxml successfully");
        } catch (IOException e) {
            System.err.println("Error loading login.fxml");
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao carregar a página de login.");
        }
    }

    private boolean validateFields() {
        System.out.println("validateFields method called");
        boolean valid = true;

        if (!validateTextField(nomeField)) valid = false;
        if (!validateTextField(nifField) || !nifField.getText().trim().matches("\\d{9}")) valid = false;
        if (!validateTextField(ruaField)) valid = false;
        if (!validateTextField(numPortaField) || !numPortaField.getText().trim().matches("\\d+")) valid = false;
        if (!validateTextField(codPostalField) || !codPostalField.getText().trim().matches("\\d{4}-\\d{3}")) valid = false;
        if (!validateTextField(telefoneField) || !telefoneField.getText().trim().matches("\\d{9}")) valid = false;
        if (!validateTextField(usernameField)) valid = false;
        if (!validateTextField(passField)) valid = false;

        if (valid) {
            System.out.println("All fields validated successfully");
        } else {
            System.out.println("Some fields validation failed");
        }

        return valid;
    }

    private boolean validateTextField(TextField textField) {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            textField.setStyle("-fx-border-color: red");
            showAlert(Alert.AlertType.ERROR, "Erro", "Campo " + textField.getPromptText() + " é obrigatório!");
            System.out.println("Field " + textField.getId() + " is empty");
            return false;
        } else {
            textField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        System.out.println("Showing alert: " + title + " - " + message);
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
