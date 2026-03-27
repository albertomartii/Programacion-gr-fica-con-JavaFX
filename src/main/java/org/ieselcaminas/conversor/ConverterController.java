package org.ieselcaminas.conversor;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ConverterController {

    private static final int DECIMALS = 4;

    // ── Conectados al FXML mediante fx:id ──────────────────────
    @FXML private TextField millasInput;
    @FXML private Label kmResultLabel;

    @FXML private TextField kmInput;
    @FXML private Label millasResultLabel;

    // ── Métodos conectados a onAction en el FXML ───────────────

    @FXML
    private void convertirMillasAKm() {
        try {
            double millas = Double.parseDouble(millasInput.getText().replace(",", "."));
            double km = DistanceConverter.milesToKm(millas);
            kmResultLabel.setText(DistanceConverter.format(km, DECIMALS));
            animar(kmResultLabel);
        } catch (NumberFormatException e) {
            kmResultLabel.setText("Valor inválido");
        }
    }

    @FXML
    private void convertirKmAMillas() {
        try {
            double km = Double.parseDouble(kmInput.getText().replace(",", "."));
            double millas = DistanceConverter.kmToMiles(km);
            millasResultLabel.setText(DistanceConverter.format(millas, DECIMALS));
            animar(millasResultLabel);
        } catch (NumberFormatException e) {
            millasResultLabel.setText("Valor inválido");
        }
    }

    // ── Animación ──────────────────────────────────────────────
    private void animar(Label label) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), label);
        st.setFromX(0.85); st.setFromY(0.85);
        st.setToX(1.0);    st.setToY(1.0);

        FadeTransition ft = new FadeTransition(Duration.millis(150), label);
        ft.setFromValue(0.4);
        ft.setToValue(1.0);

        st.play();
        ft.play();
    }
}