package org.ieselcaminas.conversor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import atlantafx.base.theme.CupertinoDark;
public class MainApp extends Application {

    /**
     * Punto de entrada REAL de JavaFX.
     * Se llama automáticamente después de launch().
     * Aquí se construye la ventana principal (Stage).
     *
     * @param stage El escenario (ventana) principal que nos proporciona JavaFX.
     */
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());

        // La ventana que vamos a mostrar
        ConverterView view = new ConverterView();
        // Cogemos del DOM la raíz para crear una escena
        Scene scene = new Scene(view.getRoot(), 780, 680);

        // El stage es la ventana
        stage.setTitle("Conversor de Distancias");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    /**
     * Método llamado ANTES de start().
     * Útil para inicializar recursos: conexión a BD, cargar configuración, etc.
     */
    @Override
    public void init() {
        System.out.println("Aplicación iniciando...");
        // Aquí podrías inicializar, por ejemplo:
        // - Conexión a base de datos
        // - Cargar un fichero de propiedades
        // - Preparar un servicio singleton
    }
    /** Ciclo de vida que ejecuta JavaFX
     main()
     └─► launch()
     ├─► init()       ← preparar recursos
     ├─► start()      ← construir y mostrar la ventana  ← aquí está todo
     └─► stop()       ← liberar recursos al cerrar
     */
    /**
     * Método llamado al CERRAR la aplicación.
     * Ideal para liberar recursos: cerrar conexiones, guardar estado, etc.
     */
    @Override
    public void stop() {
        System.out.println("Aplicación cerrando. Liberando recursos...");
        // Aquí podrías:
        // - Cerrar conexión a BD
        // - Guardar preferencias del usuario
        // - Detener hilos en segundo plano
    }

    /**
     * Punto de entrada del programa (main).
     * En JavaFX, main() simplemente llama a launch(),
     * que es quien arranca el ciclo de vida de la aplicación.
     *
     * @param args Argumentos de línea de comandos (raramente usados en JavaFX).
     */

    public static void main(String[] args) {
        launch(args);
    }

}
