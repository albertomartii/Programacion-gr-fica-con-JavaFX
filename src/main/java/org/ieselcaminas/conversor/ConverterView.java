package org.ieselcaminas.conversor;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class ConverterView {
    private static final int DECIMALS = 4;

    private VBox root;
    private TextField millasInput;
    private Label kmResultLabel;

    public ConverterView() {
        // En el constructor se llama a buildUI() por convención
        buildUI();
    }

    private void buildUI() {
        // La pantalla principal será una caja vertical con un separación entre cajas de 20
        root = new VBox(20);

        // Le ponemos padding en el interior para que no se apelotonen los controles
        root.setPadding(new Insets(32, 36, 32, 36));

        // Y la ponemos en el centro
        root.setAlignment(Pos.TOP_CENTER);

        // ── Título ──────────────────────────────────────────────
        Label titulo = new Label("Conversor de Distancias");
        titulo.setFont(Font.font("SansSerif", FontWeight.BOLD, 26));

        Label subtitulo = new Label("Millas  ↔  Kilómetros");

        VBox header = new VBox(4, titulo, subtitulo);
        header.setAlignment(Pos.CENTER);

        Separator sep1 = new Separator();

        // ── Un panel para millas ─────────────────────
        VBox panelMillas = buildConversionPanelMillas();

// ── Otro para los kilómetros ─────────────────
        VBox panelKm = buildConversionPanelKms();

// ── Los paneles los ponemos uno al lado del otro
        HBox panelesSideBySide = new HBox(16, panelMillas, panelKm);
        panelesSideBySide.setAlignment(Pos.CENTER);

// ── Los paneles crecen hasta ocupar todo el ancho
        HBox.setHgrow(panelMillas, Priority.ALWAYS);
        HBox.setHgrow(panelKm, Priority.ALWAYS);

// ── Añadimos el nuevo panelesSideBySide ──────────
        root.getChildren().addAll(
                header, sep1,
                panelesSideBySide
        );


    }
    private VBox buildConversionPanelMillas() {
        // Creamos el título
        Label panelTitulo = new Label("🏁  Millas  →  Kilómetros");
        panelTitulo.setFont(Font.font("SansSerif", FontWeight.SEMI_BOLD, 14));

        // Creamos una etiqueta para el control de de texto
        Label inputLabel = new Label("Introduce las millas:");
        millasInput = new TextField();
        millasInput.setPromptText("Ej: 10");
        // Lo hacemos máximo para que ocupe toda la caja
        millasInput.setMaxWidth(Double.MAX_VALUE);

        Label unidadLabel = new Label("kilómetros");
        // Creamos un botón que, de momento, no hace nada
        Button btnConvertir = new Button("Convertir a km");
        btnConvertir.setMaxWidth(Double.MAX_VALUE);

        // Creamos un label para mostrar el resultado
        kmResultLabel = new Label("—");
        kmResultLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
        kmResultLabel.setTextAlignment(TextAlignment.CENTER);

        // Y ahora creamos una caja vertical con una separación de 2 con el resultado
        VBox resultBox = new VBox(2, kmResultLabel, unidadLabel);
        resultBox.setAlignment(Pos.CENTER);

        // Y ahora, creamos otra caja vertical para apilar todos los controles
        VBox panel = new VBox(10, panelTitulo, inputLabel, millasInput, btnConvertir, resultBox);
        panel.setStyle("-fx-background-color: -color-bg-subtle; -fx-background-radius: 8;");
        panel.setPadding(new Insets(16));

        // Al hacer clic
        btnConvertir.setOnAction(e -> {
            convertirMillasAKm();
        });

        return panel;
    }

    public VBox getRoot(){
        return root;
    }
    private void convertirMillasAKm() {
        try {
            double millas = Double.parseDouble(millasInput.getText().replace(",", "."));
            double km = DistanceConverter.milesToKm(millas);
            String resultado = DistanceConverter.format(km, DECIMALS);
            kmResultLabel.setText(resultado);
            animarLabel(kmResultLabel);
        } catch (NumberFormatException e) {
            kmResultLabel.setText("Valor inválido");
        }
    }
    private void animarLabel(Label label) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), label);
        st.setFromX(0.85);
        st.setFromY(0.85);
        st.setToX(1.0);
        st.setToY(1.0);

        FadeTransition ft = new FadeTransition(Duration.millis(150), label);
        ft.setFromValue(0.4);
        ft.setToValue(1.0);

        st.play();
        ft.play();
    }

    /// ------------------------------------- KM A MILLAS ----------------------------------------------- ///
    // Código anterior
    private TextField kmInput;
    private Label millasResultadoLabel;
    // Código anterior
    private VBox buildConversionPanelKms() {
        Label panelTitulo = new Label("📍  Kilómetros  →  Millas");
        panelTitulo.setFont(Font.font("SansSerif", FontWeight.SEMI_BOLD, 14));

        Label inputLabel = new Label("Introduce los kilómetros:");

        kmInput = new TextField();
        kmInput.setPromptText("Ej: 10");
        kmInput.setMaxWidth(Double.MAX_VALUE);

        Label unidadLabel = new Label("millas");
        Button btnConvertir = new Button("Convertir a millas");
        btnConvertir.setMaxWidth(Double.MAX_VALUE);

        btnConvertir.setOnAction(e -> {
            convertirKmAMillas();
        });

        millasResultadoLabel = new Label("—");
        millasResultadoLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
        millasResultadoLabel.setTextAlignment(TextAlignment.CENTER);

        VBox resultBox = new VBox(2, millasResultadoLabel, unidadLabel);
        resultBox.setAlignment(Pos.CENTER);

        VBox panel = new VBox(10, panelTitulo, inputLabel, kmInput, btnConvertir, resultBox);
        panel.setStyle("-fx-background-color: -color-bg-subtle; -fx-background-radius: 8;");
        panel.setPadding(new Insets(16));

        return panel;
    }
    private void convertirKmAMillas() {
        try {
            double km = Double.parseDouble(kmInput.getText().replace(",", "."));
            double millas = DistanceConverter.kmToMiles(km);
            String resultado = DistanceConverter.format(millas, DECIMALS);
            millasResultadoLabel.setText(resultado);
            animarLabel(millasResultadoLabel);
        } catch (NumberFormatException e) {
            millasResultadoLabel.setText("Valor inválido");
        }
    }



}
