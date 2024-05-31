package com.example.desktop;

import BLL.AdminBll;
import BLL.ClienteBLL;
import entity.Admin;
import entity.Cliente;
import entity.PasswordUtils;
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
import java.security.NoSuchAlgorithmException;

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

            System.out.println("Trying to login with username: " + username);

            Cliente cliente = ClienteBLL.findClienteByUsername(username);

            if (cliente != null) {
                System.out.println("Found cliente: " + cliente.getNome());
                String storedPassword = cliente.getSenha();
                String[] parts = storedPassword.split(":");
                String hash = parts[0];
                String salt = parts[1];

                try {
                    String hashedPassword = PasswordUtils.hashPassword(password, salt);
                    if (hashedPassword.equals(hash)) {
                        showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + cliente.getNome() + "!");
                        redirectToClienteMenu();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                    }
                } catch (NoSuchAlgorithmException e) {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Error processing password.");
                }
            }
            else {
                System.out.println("No cliente found, trying admin.");
                Admin admin = AdminBll.findAdminByUsername(username);

                if (admin != null) {
                    System.out.println("Found admin: " + admin.getUsername());
                    if (admin.getSenha().equals(password)) {
                        showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");
                        redirectToAdminMenu();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                }
            }
        });

        registerButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/registarCliente.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void redirectToAdminMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Admin/adminMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectToClienteMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/clienteMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
