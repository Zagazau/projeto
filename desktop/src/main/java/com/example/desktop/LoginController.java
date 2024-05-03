package com.example.desktop;

import BLL.AdminBll;
import BLL.ClienteBLL;
import entity.Admin;
import entity.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

            Cliente cliente = ClienteBLL.findClienteByUsername(username);

            if (cliente == null) {
                Admin admin = AdminBll.findAdminByUsername(username);

                if (admin != null && admin.getSenha().equals(password)) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/adminMenu.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) loginButton.getScene().getWindow(); // Obtém o Stage atual
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                }
            } else {
                if (cliente.getSenha().equals(password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + cliente.getNome() + "!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                }
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
