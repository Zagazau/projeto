package com.example.desktop;

import BLL.ClienteBLL;
import entity.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Verificar se o cliente existe na base de dados
            Cliente cliente = ClienteBLL.findClienteByUsername(username);

            if (cliente != null && cliente.getSenha().equals(password)) {
                // Login bem sucedido
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + cliente.getNome() + "!");
               // Parent root = FXMLLoader.load(getClass().getResource("../resources/com/example/desktop/"));
            } else {
                // Login falhou
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        });

        registerButton.setOnAction(event -> {
            // Aqui você pode adicionar a lógica para abrir a tela de registro
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
