package org.ieselcaminas.conversor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import atlantafx.base.theme.CupertinoDark;
import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());

        // ── Cargamos el FXML en vez de instanciar ConverterView ──
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("main-view.fxml")
        );
        Scene scene = new Scene(loader.load(), 780, 680);

        stage.setTitle("Conversor de Distancias");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void init() {
        System.out.println("Aplicación iniciando...");
    }

    @Override
    public void stop() {
        System.out.println("Aplicación cerrando. Liberando recursos...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}