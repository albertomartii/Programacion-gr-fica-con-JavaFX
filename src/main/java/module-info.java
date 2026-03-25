module org.ieselcaminas.conversor.pasoapaso {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;
    requires atlantafx.base;

    opens org.ieselcaminas.conversor to javafx.fxml;
    exports org.ieselcaminas.conversor;
}