package com.example.desktop;

import BLL.AdminBll;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        AdminBll.createDefaultAdminIfNotExists();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 655.0, 500.0);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


}