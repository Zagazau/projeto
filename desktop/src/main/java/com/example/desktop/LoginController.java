package com.example.desktop;

import BLL.AdminBll;
import BLL.ClienteBLL;
import BLL.FornecedorBll;
import com.example.desktop.clientes.MenuClienteController;
import entity.Admin;
import entity.Cliente;
import entity.Fornecedor;
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
                        showAlert(Alert.AlertType.INFORMATION, "Login com sucesso", "Bem vindo, " + cliente.getNome() + "!");
                        redirectToClienteMenu(cliente.getIdcliente());
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erro", "Password ou username inválidos.");
                    }
                } catch (NoSuchAlgorithmException e) {
                    showAlert(Alert.AlertType.ERROR, "Login Falhou", "Erro a processar Password");
                }
            } else {
                System.out.println("No cliente found, trying admin.");
                Admin admin = AdminBll.findAdminByUsername(username);

                if (admin != null) {
                    System.out.println("Found admin: " + admin.getUsername());
                    if (admin.getSenha().equals(password)) {
                        showAlert(Alert.AlertType.INFORMATION, "Login com sucesso", "Bem vindo, Admin!");
                        redirectToAdminMenu();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erro", "Password ou username inválidos.");
                    }
                } else {
                    System.out.println("No admin found, trying fornecedor.");
                    Fornecedor fornecedor = FornecedorBll.findFornecedorByUsername(username);

                    if (fornecedor != null) {
                        System.out.println("Found fornecedor: " + fornecedor.getNome());
                        String storedPassword = fornecedor.getSenha();
                        String[] parts = storedPassword.split(":");
                        String hash = parts[0];
                        String salt = parts[1];

                        try {
                            String hashedPassword = PasswordUtils.hashPassword(password, salt);
                            if (hashedPassword.equals(hash)) {
                                showAlert(Alert.AlertType.INFORMATION, "Login com sucesso", "Bem vindo, " + fornecedor.getNome() + "!");
                                redirectToFornecedorMenu();
                            } else {
                                showAlert(Alert.AlertType.ERROR, "Erro", "Password ou username inválidos.");
                            }
                        } catch (NoSuchAlgorithmException e) {
                            showAlert(Alert.AlertType.ERROR, "Login Failed", "Error processing password.");
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                    }
                }
            }
        });

        registerButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Cliente/registarUtilizador.fxml"));
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

    private void redirectToClienteMenu(int idCliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/desktop/Cliente/clienteMenu.fxml"));
            Parent root = loader.load();
            MenuClienteController controller = loader.getController();
            controller.setIdCliente(idCliente);
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectToFornecedorMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/desktop/Fornecedor/fornecedorMenu.fxml"));
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
